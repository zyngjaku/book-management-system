package system.management.book.services;

import java.sql.*;
import java.util.LinkedList;

public class DB {
    private String host = "mysql.agh.edu.pl";
    private String username = "zyngier1";
    private String password = "4FcqT2V60H3hSVEJ";
    private String database = "zyngier1";

    private Connection conn = null;

    public DB() {

    }

    public String getBookAuthors(int id_book) {
        StringBuilder bookAuthorsStringBuilder = new StringBuilder();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT a.name, a.surname FROM Book_authors AS ba JOIN Authors AS a ON ba.id_author=a.id_author WHERE ba.id_book=" + id_book + ";");

            while(rs.next()) {
                bookAuthorsStringBuilder.append(rs.getString(1)).append(" ").append(rs.getString(2));

                if(!rs.isLast()) {
                    bookAuthorsStringBuilder.append(", ");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookAuthorsStringBuilder.toString();
    }

    public LinkedList<TableTop10> getTop10Books() {
        LinkedList<TableTop10> listTop10Books = new LinkedList<>();

        try {
            openConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.id_book, b.title, AVG(c.rate) AS 'average' FROM Comments AS c JOIN Books AS b ON c.id_book=b.id_book GROUP BY c.id_book ORDER BY average DESC LIMIT 10;");

            int lp=1;
            while(rs.next()) {
                String title = rs.getString(2);
                double rate = Math.round(rs.getDouble(3) * 100.0) / 100.0;
                String author = getBookAuthors(rs.getInt(1));
                listTop10Books.add(new TableTop10(lp, title, author, rate));

                lp++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return listTop10Books;
    }

    public Boolean checkIfLogInIsCorrect(String mail, String password) {
        try {
            openConnection();

            Sha1 sha1 = new Sha1();
            String passwordEncoded = sha1.encode(password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_user FROM Users WHERE mail='" + mail + "' AND password='" + passwordEncoded + "';");

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return false;
    }

    public boolean checkIfEmailNotExistAndCreateUser(String mail, String password) {
        try {
            openConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT mail FROM Users WHERE mail='" + mail + "'");

            if(rs.next())
                return false;

            Sha1 sha1 = new Sha1();
            String passwordEncoded = sha1.encode(password);

            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Users (mail, password) VALUES('" + mail + "', '" + passwordEncoded + "');");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return true;
    }

    public void openConnection(){
        if(conn==null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);

            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
