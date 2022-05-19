package sample;

import java.sql.Date;

public class borrow {
    private String bookID;
    private String title;
    private String author;
    private String edition_date;
    private int userID;
    private String first_name;
    private String last_name;
    private String issue_date;
    private String return_date;
    //constroctor
    public borrow(String bookID, String title, String author, String edition_date, int userID, String first_name, String last_name, String issue_date, String return_date) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.edition_date = edition_date;
        this.userID = userID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.issue_date = issue_date;
        this.return_date = return_date;
    }
    public borrow(String bookID, String title, String author, String edition_date, String issue_date, String return_date){
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.edition_date = edition_date;
        this.issue_date = issue_date;
        this.return_date = return_date;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
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

    public String getEdition_date() {
        return edition_date;
    }

    public void setEdition_date(String edition_date) {
        this.edition_date = edition_date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}
