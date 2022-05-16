package sample;

public class borrow {
    private String bookID;
    private String title;
    private String author;
    private String edition_date;
    private String userID;
    private String first_name;
    private String last_name;
    private String status;
    private String issue_date;
    private String return_date;
    //constroctor
    public borrow(String bookID, String title, String author, String edition_date, String userID, String first_name, String last_name, String status, String issue_date, String return_date) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.edition_date = edition_date;
        this.userID = userID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.status = status;
        this.issue_date = issue_date;
        this.return_date = return_date;
    }
}
