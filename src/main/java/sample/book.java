package sample;

public class book {
    private String ark;
    private String title;
    private String author;
    private int year;
    //constructor
    public book(String ark, String title, String author, int year) {
        this.ark = ark;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    //getters and setters
    public String getArk() {
        return ark;
    }

    public void setArk(String ark) {
        this.ark = ark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
