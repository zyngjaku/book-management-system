package system.management.book.services;

import java.util.Date;

public class Book {
    private int lp;
    private String title;
    private String author;
    private String description;
    private double rate;
    private Date release;
    private int pages;
    private String genres;

    public Book(){
    }

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

    public Book(int lp, String title, String author, String description, Date release, int pages){
        this(lp, title, author, description, release);

        this.pages=pages;
    }

    public Book(int lp, String title, String author, double rate, String description, Date release, int pages){
        this(lp, title, author, description, release, pages);

        this.rate=rate;
    }

    public Book(int lp, String title, String author, double rate, String description, Date release, int pages, String genres){
        this(lp, title, author, rate, description, release, pages);

        this.genres=genres;
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

    public int getPages() {
        return pages;
    }

    public String getGenres() {
        return genres;
    }

}
