package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class librarian1 implements Initializable {
    ///////////////////////My profile
    user user = new user();

    //SetUser Profile
    @FXML
    private Text adress;

    @FXML
    private Button backButtonProfile;

    @FXML
    private Button disconnectButtonProfile;

    @FXML
    private Text first_name;

    @FXML
    private Text last_name;

    @FXML
    private Text role;

    @FXML
    private Text userID;
    //instance of setScene class
    setScene scene = new setScene();
    @FXML
    void disconnect(ActionEvent event) throws IOException {
        scene.disconnect(event);

    }

    @FXML
    public void profileBack(ActionEvent event) throws IOException {
        String path;
        if (role.getText().equals("student")){
            path="/student.fxml";
        }else {
            path="/librarian.fxml";
        }
        scene.setScene(event,path);
    }

    public void setUserProfile(int id,String f,String l,String a, String r) {
        userID.setText(String.valueOf(id));
        first_name.setText(f);
        last_name.setText(l);
        adress.setText(a);
        role.setText(r);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////Search book
    @FXML
    private ComboBox choiceBook;

    @FXML
    public ObservableList<String> list = FXCollections.observableArrayList("title", "author", "edition date", "language");


    @FXML
    private TableColumn<book, String> id;

    @FXML
    private Button ReturnButton;

    @FXML
    private Button SearchBookButton;

    @FXML
    private TableView<book> table;

    @FXML
    private TableColumn<book, String> author;

    @FXML
    private TableColumn<book, String> date;

    @FXML
    private ImageView filter;

    @FXML
    private TextField filterField;

    @FXML
    private TableColumn<book, String> language;

    @FXML
    private TableColumn<book, String> status;

    @FXML
    private TableColumn<book, String> title;
    @FXML
    public ObservableList<book> data = FXCollections.observableArrayList();
    String st;

    @FXML
    public void filter(ActionEvent event) throws SQLException {
        try {
            Connect conn = new Connect();

            String choice = choiceBook.getSelectionModel().getSelectedItem().toString();
            String input = filterField.getText();
            String result="";
            switch (choice) {
                case "author":
                    result = "dc.creator";
                    break;
                case "title":
                    result = "dc.title";
                    break;
                case "edition date":
                    result = "dc.date";
                    break;
                case "language":
                    result = "dc.language";
                    break;
                default:
                    result = "dc.author";
                    break;
            }
            try {
                String url = "https://gallica.bnf.fr/SRU?operation=searchRetrieve&version=1.2&query=(" + result + "%20all%20" + input + ")%20and%20(dc.type%20all%20%22monographie%22)";
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
                //in.close();
                //print in String
                // System.out.println(response.toString());
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .parse(new InputSource(new StringReader(response.toString())));
                NodeList errNodes = doc.getElementsByTagName("srw:record");
                int i = 0;
                while (errNodes.getLength() > 0) {
                    Element err = (Element) errNodes.item(i);
                    i++;
                    PreparedStatement stat = conn.connection().prepareStatement("select * from loans where bookID=? and DATEDIFF(date_Return,date_Borrow)=25");
                    stat.setString(1,err.getElementsByTagName("uri").item(0).getTextContent());
                    ResultSet res = stat.executeQuery();
                    if (res.next()){
                        st = "not available";
                    }else{
                        st = "available";
                    }
                    book book = new book(err.getElementsByTagName("uri").item(0).getTextContent(),err.getElementsByTagName("dc:title").item(0).getTextContent(),err.getElementsByTagName("dc:publisher").item(0).getTextContent(),err.getElementsByTagName("dc:date").item(0).getTextContent(),err.getElementsByTagName("dc:language").item(1).getTextContent(),st);
                    data.add(book);
                /*System.out.println("author : "+err.getElementsByTagName("dc:creator").item(0).getTextContent());
                System.out.println("title : "+err.getElementsByTagName("dc:title").item(0).getTextContent());
                System.out.println("date : "+err.getElementsByTagName("dc:date").item(0).getTextContent());
                System.out.println("language : "+err.getElementsByTagName("dc:language").item(1).getTextContent());*/

                }
                // success

            } catch (Exception e) {
                System.out.println(e);
            }
        }catch (Exception e){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You should select a choice in FilterBox!");
            alert.showAndWait();
        }


    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////Manage users

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////Manage loans
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
    private TableColumn<?, ?> dateLoan;

    @FXML
    private TableColumn<?, ?> date1;

    @FXML
    private TableColumn<?, ?> idLoan;

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
    private TextField userIDIssue;

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
        try{
            String sql = "select * from users where userID = ? and role='student' ";
            PreparedStatement stat = con.connection().prepareStatement(sql);
            stat.setString(1, userIDIssue.getText());
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
        }catch (Exception E){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("We don't have acces to database!");
            alert.showAndWait();

        }




    }

    public void borrow(ActionEvent event) throws SQLException {
        try{
            Connect con = new Connect();
            PreparedStatement statement1 = con.connection().prepareStatement("select * from loans where bookID = ? and DATEDIFF(loans.date_Return,loans.date_Borrow)=25");
            statement1.setString(1,bookID.getText());
            ResultSet res1 = statement1.executeQuery();
            if (res1.next()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("This Book is not available!");
                alert.showAndWait();
            }else{
                PreparedStatement statement = con.connection().prepareStatement("select * from loans where userID = ? and DATEDIFF(loans.date_Return,loans.date_Borrow)=25");
                statement.setString(1, String.valueOf(res1.getInt(2)));
                ResultSet res = statement.executeQuery();
                int i=0;
                while (res.next()){
                    i++;
                }
                if ( i <= 8){
                    PreparedStatement stat = con.connection().prepareStatement("insert into loans values  (default ,?,?,?,?)");
                    stat.setString(1,userIDIssue.getText());
                    stat.setString(2,bookID.getText());
                    stat.setString(3, String.valueOf(issue1DateIssue.getValue()));
                    LocalDate date_return = issue1DateIssue.getValue().plusDays(25);
                    stat.setString(4, String.valueOf(date_return));
                    stat.execute();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Loan added succefuly !");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("This student has borrowed 8 books already!");
                    alert.showAndWait();
                }
            }

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Check if you entered all informations required!");
            alert.showAndWait();
        }



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
    private TableColumn<borrow, String> authorLoan;
    @FXML
    private TableColumn<borrow, String> titleLoan;
    @FXML
    private TableColumn<borrow, Integer> studentid;

    @FXML
    private TableColumn<borrow, String> bookid;

    @FXML
    private TableColumn<borrow, String> editiondate;

    @FXML
    private TableColumn<borrow, String> firstname;

    @FXML
    public ObservableList<borrow> dataLoan = FXCollections.observableArrayList();
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
            PreparedStatement statement = conn.connection().prepareStatement("select * from users where userID =? ");
            statement.setString(1, String.valueOf(res.getInt(2)));
            ResultSet res1 = statement.executeQuery();
            if (res1.next()){
                borrow loan = new borrow(res.getString(3), err.getElementsByTagName("dc:title").item(0).getTextContent(), err.getElementsByTagName("dc:publisher").item(0).getTextContent(), err.getElementsByTagName("dc:date").item(0).getTextContent(), res.getInt(2), res1.getString(2), res1.getString(3), res.getString(4), res.getString(5));
                dataLoan.add(loan);
            }

        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        studentid.setCellValueFactory(new PropertyValueFactory<borrow, Integer>("userID"));
        titleLoan.setCellValueFactory(new PropertyValueFactory<borrow, String>("title"));
        authorLoan.setCellValueFactory(new PropertyValueFactory<borrow, String>("author"));
        editiondate.setCellValueFactory(new PropertyValueFactory<borrow, String>("edition_date"));
        firstname.setCellValueFactory(new PropertyValueFactory<borrow, String>("first_name"));
        lastname.setCellValueFactory(new PropertyValueFactory<borrow, String>("last_name"));
        bookid.setCellValueFactory(new PropertyValueFactory<borrow, String>("bookID"));
        issuedate.setCellValueFactory(new PropertyValueFactory<borrow, String>("issue_date"));
        limitdate.setCellValueFactory(new PropertyValueFactory<borrow, String>("return_date"));

        issue2SearchTableView.setItems(dataLoan);


    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void returnBook(ActionEvent event) throws SQLException {
        Connect conn = new Connect();
        try{
            PreparedStatement stat = conn.connection().prepareStatement("select * from loans where bookID = ? and DATEDIFF(loans.date_Return,loans.date_Borrow)=25 ");
            stat.setString(1, return1Bookid.getText());
            ResultSet res = stat.executeQuery();
            if (res.next()){
                if(return1ReturnDate.getValue().equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("please verify the book ID, There is no loan assigned !");
                alert.showAndWait();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("please Check if you entered all informations required!");
            alert.showAndWait();
        }



    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////Manage users
    public void refreshTable(ActionEvent event) {
    }

    public void getAddView(ActionEvent event) {
    }
}
