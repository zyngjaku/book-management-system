package system.management.book.services;

import java.util.Date;

public class Book {
    private int lp;
    private int idBook;
    private String title;
    private String author;
    private String description;
    private double rate;
    private Date release;
    private int pages;
    private String genres;

    public Book() {

    }

    public Book(int idBook, String title, String author, double rate, String description, Date release, int pages, String genres){
        this.idBook = idBook;
        this.title=title;
        this.author=author;
        this.rate=rate;
        this.description=description;
        this.release=release;
        this.pages=pages;
        this.genres = genres;
    }

    public Book(int lp, int idBook, String title, String author, double rate, String description, Date release, int pages, String genres){
        this(idBook, title, author, rate, description, release, pages, genres);

        this.lp = lp;
    }

    public int getLp() {
        return lp;
    }

    public int getIdBook() {
        return idBook;
}

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
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
