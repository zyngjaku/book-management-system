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

    public String getBookGenres(int id_book) {
        StringBuilder bookGenresStringBuilder = new StringBuilder();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT g.genre FROM Book_genres AS bg JOIN Genres AS g ON bg.id_genre=g.id_genre WHERE bg.id_book=" + id_book + ";");

            while(rs.next()) {
                bookGenresStringBuilder.append(rs.getString(1));

                if(!rs.isLast()) {
                    bookGenresStringBuilder.append(", ");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookGenresStringBuilder.toString();
    }

    public LinkedList<Book> getTop10Books() {
        LinkedList<Book> listTop10Books = new LinkedList<>();

        try {
            openConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.id_book, AVG(c.rate) AS 'average' FROM Comments AS c JOIN Books AS b ON c.id_book=b.id_book GROUP BY c.id_book ORDER BY average DESC LIMIT 10;");

            int lp=1;
            while(rs.next()) {
                listTop10Books.add(getAllInformationAboutSpecificBooks(lp, rs.getInt(1)));

                lp++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return listTop10Books;
    }

    public LinkedList<Book> getAuthorBooks(int id_author) {
        LinkedList<Book> listAuthorBooks = new LinkedList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_book FROM Book_authors WHERE id_author=" + id_author + ";");

            int lp=1;
            while(rs.next()) {
                int id_book = rs.getInt(1);
                listAuthorBooks.add(getAllInformationAboutSpecificBooks(lp, id_book));

                lp++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listAuthorBooks;
    }

    public LinkedList<Author> getAllAutors() {
        LinkedList<Author> listOfAllAuthors = new LinkedList<>();

        try {
            openConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_author, name, surname, birthday FROM Authors ORDER BY surname, name;");

            int lp=1;
            while(rs.next()) {
                int id_author = rs.getInt(1);
                String name = rs.getString(3) + " " + rs.getString(2);
                Date birthday = rs.getDate(4);

                listOfAllAuthors.add(new Author(lp, name, birthday, getAuthorBooks(id_author)));
                lp++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return listOfAllAuthors;
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

    public LinkedList<Book> getAllBooks() {
        LinkedList<Book> listBooks = new LinkedList<>();

        try {
            openConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_book FROM Books ORDER BY title;");

            int lp=1;
            while(rs.next()) {
                listBooks.add(getAllInformationAboutSpecificBooks(lp, rs.getInt(1)));

                lp++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return listBooks;
    }

    public LinkedList<Book> getSearchedBooks(String phrase) {
        LinkedList<Book> listSearchedBooks = new LinkedList<>();

        try {
            openConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT b.id_book FROM Books AS b JOIN Book_authors AS ba ON b.id_book = ba.id_book JOIN Authors AS a ON ba.id_author = a.id_author WHERE b.title LIKE '%" + phrase + "%' OR a.name LIKE '%" + phrase + "%' OR a.surname LIKE '%" + phrase + "%' GROUP BY b.id_book;");

            int lp=1;
            while(rs.next()) {
                listSearchedBooks.add(getAllInformationAboutSpecificBooks(lp, rs.getInt(1)));

                lp++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection();

        return listSearchedBooks;
    }

    public Book getAllInformationAboutSpecificBooks(int lp, int id_book) {
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT b.id_book, b.title, b.description, b.release_year, AVG(c.rate) AS 'average', b.pages as 'average' FROM Books AS b LEFT JOIN Comments AS c ON b.id_book=c.id_book WHERE b.id_book=" + id_book + " GROUP BY c.id_book;");

            while(rs.next()) {
                String author = getBookAuthors(id_book);
                String genres = getBookGenres(id_book);
                String title = rs.getString(2);
                String description = rs.getString(3);
                Date release_year = rs.getDate(4);
                double rate = Math.round(rs.getDouble(5) * 100.0) / 100.0;
                int pages = rs.getInt(6);

                return new Book(lp, title, author, rate, description, release_year, pages, genres);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Book();
    }

    private void openConnection(){
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

    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
