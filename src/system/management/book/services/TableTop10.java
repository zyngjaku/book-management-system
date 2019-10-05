package system.management.book.services;

public class TableTop10 {
    int lp;
    String title;
    String author;
    double rate;

    public TableTop10(int lp, String title, String author, double rate){
        this.lp=lp;
        this.title=title;
        this.author=author;
        this.rate=rate;
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

    public double getRate() {
        return rate;
    }


}
