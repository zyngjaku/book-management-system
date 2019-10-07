package system.management.book.services;

import java.util.Date;

public class Book {
    int lp;
    String title;
    String author;
    String description;
    double rate;
    Date release;

    public Book(int lp, String title, String author){
        this.lp=lp;
        this.title=title;
        this.author=author;
    }

    public Book(int lp, String title, String author, double rate){
        this(lp, title, author);

        this.rate=rate;
    }

    public Book(int lp, String title, String author, Date release){
        this(lp, title, author);

        this.release=release;
    }

    public Book(int lp, String title, String author, String description, Date release){
        this(lp, title, author, release);

        this.description=description;
    }

    public int getLp() {
        return lp;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public double getRate() {
        return rate;
    }

    public Date getRelease() {
        return release;
    }

}
