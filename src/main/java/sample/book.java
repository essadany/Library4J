package sample;

public class book {
    private String status;
    private String ark;
    private String title;
    private String author;
    private String year;
    private String language;
    //constructor


    public book(String ark, String title, String author, String year, String language,String status) {
        this.ark = ark;
        this.title = title;
        this.author = author;
        this.year = year;
        this.language = language;
        this.status = status;
    }


    //getters and setters


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
