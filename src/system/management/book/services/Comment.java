package system.management.book.services;

import java.util.Date;

public class Comment {
    private Date date;
    private String name;
    private String surname;
    private String comment;
    private double rate;

    public Comment(Date date, String name, String surname, String comment, double rate) {
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.comment = comment;
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {//dla id ksiazki wszystkie koment i info
        return surname;
    }

    public String getComment() {
        return comment;
    }

    public double getRate() {
        return rate;
    }
}
