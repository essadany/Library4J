package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class manageLoans implements Initializable {
    @FXML
    private TableColumn<?, ?> IdBookCol;

    @FXML
    private TableColumn<?, ?> IdBookCol1;

    @FXML
    private Button ReturnButton2;

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
    private TableColumn<?, ?> titile1;

    @FXML
    private TextField userID;

    @FXML
    public void searchBook(ActionEvent event) {
        try {
            String url = "https://gallica.bnf.fr/services/OAIRecord?ark="+bookID.getText();
            System.out.println(url);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            // System.out.println(response.toString());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));
            NodeList errNodes = doc.getElementsByTagName("oai_dc:dc");
            int i = 0;
            Element err = (Element) errNodes.item(0);
            issue1Author.setText(err.getElementsByTagName("dc:publisher").item(0).getTextContent());
            issue1Title.setText(err.getElementsByTagName("dc:title").item(0).getTextContent());
            issue1DoE.setText(err.getElementsByTagName("dc:date").item(0).getTextContent());
            issue1Language.setText(err.getElementsByTagName("dc:language").item(1).getTextContent());
            // success

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("There is no any book with this ID");
            alert.showAndWait();
        }

    }

    @FXML
    void searchStudent(ActionEvent event) throws SQLException {
        Connect con = new Connect();
        String sql = "select * from users where userID = ? ";
        PreparedStatement stat = con.connection().prepareStatement(sql);
        stat.setString(1, userID.getText());
        ResultSet rs = stat.executeQuery();
        if (rs.next()) {
            issue1FirstName.setText(rs.getString(2));
            issue1LastName.setText(rs.getString(3));
            issue1Adress.setText(rs.getString(4));
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("There is no any student with this ID");
            alert.showAndWait();
        }



    }

    public void borrow(ActionEvent event) throws SQLException {
        Connect con = new Connect();
        PreparedStatement stat = con.connection().prepareStatement("insert into loans values  (DEFAULT,?,?,?,NULL)");
        stat.setString(1,userID.getText());
        stat.setString(2,bookID.getText());
        stat.setString(3, String.valueOf(issue1DateIssue.getValue()));
        DateFormat date_return = new SimpleDateFormat();
        /*LocalDate cal = issue1DateIssue.getValue();
        date_return.format(cal);
        cal.add(Calendar.DATE, 30);
        stat.setString(4, String.valueOf(issue1DateIssue.getValue()));*/
        stat.execute();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Loan added succefuly !");
        alert.showAndWait();

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField issue2SearchBar;

    @FXML
    private Button issue2SearchButton;

    @FXML
    private TableView<borrow> issue2SearchTableView;

    @FXML
    private Tab issue2Tab;
    @FXML
    private TableColumn<borrow, String> issuedate;

    @FXML
    private TableColumn<borrow, String> lastname;

    @FXML
    private TableColumn<borrow, String> limitdate;
    @FXML
    private TableColumn<borrow, String> author;
    @FXML
    private TableColumn<borrow, String> title;
    @FXML
    private TableColumn<borrow, Integer> studentid;

    @FXML
    private TableColumn<borrow, String> bookid;

    @FXML
    private TableColumn<borrow, String> editiondate;

    @FXML
    private TableColumn<borrow, String> firstname;

    @FXML
    public ObservableList<borrow> data = FXCollections.observableArrayList();
    @FXML
    public void issueTable() throws IOException, ParserConfigurationException, SQLException, SAXException {
        Connect conn = new Connect();
        Statement stat = conn.connection().createStatement();
        ResultSet res = stat.executeQuery("select * from loans");
        while (res.next()) {
            String url2 = "https://gallica.bnf.fr/services/OAIRecord?ark=" + res.getString(3);
            System.out.println(url2);
            URL obj = new URL(url2);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            // System.out.println(response.toString());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));
            NodeList errNodes = doc.getElementsByTagName("oai_dc:dc");
            Element err = (Element) errNodes.item(0);
            // success
            Statement statement = conn.connection().createStatement();
            ResultSet res1 = statement.executeQuery("select * from users where userID = " + res.getInt(2));
            if (res1.next()){
                borrow loan = new borrow(res.getString(3), err.getElementsByTagName("dc:title").item(0).getTextContent(), err.getElementsByTagName("dc:publisher").item(0).getTextContent(), err.getElementsByTagName("dc:date").item(0).getTextContent(), res.getInt(2), res1.getString(2), res1.getString(3), res.getString(4), res.getString(5));
                data.add(loan);
            }

        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        studentid.setCellValueFactory(new PropertyValueFactory<borrow, Integer>("userID"));
        title.setCellValueFactory(new PropertyValueFactory<borrow, String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<borrow, String>("author"));
        editiondate.setCellValueFactory(new PropertyValueFactory<borrow, String>("edition_date"));
        firstname.setCellValueFactory(new PropertyValueFactory<borrow, String>("first_name"));
        lastname.setCellValueFactory(new PropertyValueFactory<borrow, String>("last_name"));
        bookid.setCellValueFactory(new PropertyValueFactory<borrow, String>("bookID"));
        issuedate.setCellValueFactory(new PropertyValueFactory<borrow, String>("issue_date"));
        limitdate.setCellValueFactory(new PropertyValueFactory<borrow, String>("return_date"));

        issue2SearchTableView.setItems(data);


    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void returnBook(ActionEvent event) throws SQLException {
        Connect conn = new Connect();
        PreparedStatement stat = conn.connection().prepareStatement("select * from loans where userID =? and bookID = ? and date_Return=date_Borrow");
        stat.setString(1, return1Studentid.getText());
        stat.setString(2, return1Bookid.getText());
        ResultSet res = stat.executeQuery();
        if (res.next()){
            if(return1ReturnDate.getValue().equals("")){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("please enter return Date!");
                alert.showAndWait();
            }else{
                PreparedStatement statement = conn.connection().prepareStatement("update loans set date_Return = ? where idLoan = ?");
                statement.setString(1, String.valueOf(return1ReturnDate.getValue()));
                statement.setString(2, String.valueOf(res.getInt(1)));
                statement.execute( );
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("retrun book succed");
                alert.showAndWait();

            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("please verify the informations, There is no loan assigned !");
            alert.showAndWait();
        }


    }
    @FXML
    public void backInterface(ActionEvent event) throws IOException {
        setScene scene = new setScene();
        scene.setScene(event,"/librarian.fxml");
    }
}
