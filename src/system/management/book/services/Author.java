package system.management.book.services;

import java.util.Date;
import java.util.LinkedList;

public class Author {
    private int lp;
    private String name;
    private Date birthday;
    private LinkedList<Book> listOfBooks;
    private int numberOfBooks;

    public Author(int lp, String name, Date birthday) {
        this.lp = lp;
        this.name = name;
        this.birthday = birthday;
    }

    public Author(int lp, String name, Date birthday, LinkedList<Book> listOfBooks) {
        this(lp, name, birthday);

        this.listOfBooks = listOfBooks;
        this.numberOfBooks = listOfBooks.size();
    }

    public void addBook(Book book) {
        listOfBooks.add(book);
    }

    public int getLp() {
        return lp;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public LinkedList<Book> getListOfBooks() {
        return listOfBooks;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }
}
