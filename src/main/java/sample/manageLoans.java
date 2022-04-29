package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class manageLoans {
    @FXML
    private TableColumn<?, ?> IdBookCol;

    @FXML
    private TableColumn<?, ?> IdBookCol1;

    @FXML
    private Button ReturnButton2;

    @FXML
    private TableColumn<?, ?> author;

    @FXML
    private TableColumn<?, ?> author1;

    @FXML
    private TextField bookID;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> date1;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> id1;

    @FXML
    private Label issue1Adress;

    @FXML
    private Label issue1Author;

    @FXML
    private ImageView issue1BackButton;

    @FXML
    private DatePicker issue1DateIssue;

    @FXML
    private Label issue1DoE;

    @FXML
    private Label issue1FirstName;

    @FXML
    private Label issue1Language;

    @FXML
    private Label issue1LastName;

    @FXML
    private Button issue1SearchBookButton;

    @FXML
    private Button issue1SearchStudentButton;

    @FXML
    private Tab issue1Tab;

    @FXML
    private Label issue1Title;

    @FXML
    private Button issue1issueButton;

    @FXML
    private ChoiceBox<?> issue2FilterChoiceBox;

    @FXML
    private TextField issue2SearchBar;

    @FXML
    private Button issue2SearchButton;

    @FXML
    private TableView<?> issue2SearchTableView;

    @FXML
    private Tab issue2Tab;

    @FXML
    private Button issue2returnButton;

    @FXML
    private TableColumn<?, ?> language;

    @FXML
    private TableColumn<?, ?> language1;

    @FXML
    private TabPane loansPane;

    @FXML
    private Label return1Adress;

    @FXML
    private Label return1Author;

    @FXML
    private Button return1BackButton;

    @FXML
    private TextField return1Bookid;

    @FXML
    private Label return1DoE;

    @FXML
    private Label return1DoI;

    @FXML
    private Label return1FirstName;

    @FXML
    private Label return1Language;

    @FXML
    private Label return1LastName;

    @FXML
    private Button return1ReturnButton;

    @FXML
    private DatePicker return1ReturnDate;

    @FXML
    private Label return1Role;

    @FXML
    private Button return1SearchButton;

    @FXML
    private TextField return1Studentid;

    @FXML
    private Tab return1Tab;

    @FXML
    private Label return1Title;

    @FXML
    private ChoiceBox<?> return2FilterChoiceBox;

    @FXML
    private TextField return2SearchBar;

    @FXML
    private Button return2SearchButton;

    @FXML
    private Tab return2Tab;

    @FXML
    private TableView<?> return2TableView;

    @FXML
    private Button return2returnButton;

    @FXML
    private TableColumn<?, ?> titile;

    @FXML
    private TableColumn<?, ?> titile1;

    @FXML
    private TextField userID;
    Connect con = new Connect();
    @FXML
    void searchBook(ActionEvent event) {

    }

    @FXML
    void searchStudent(ActionEvent event) throws SQLException {
        String sql = "select * from users where userID = ? ";
        PreparedStatement stat = con.connection().prepareStatement(sql);
        stat.setString(1, userID.getText());
        ResultSet rs = stat.executeQuery();
        while (rs.next()) {
            issue1FirstName.setText(rs.getString(2));
            issue1LastName.setText(rs.getString(3));
            issue1Adress.setText(rs.getString(4));
        }


    }

}
