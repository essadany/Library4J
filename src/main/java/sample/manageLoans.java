package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
            issue1Language.setText(err.getElementsByTagName("dc:language").item(0).getTextContent());
            // success

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("There is no any book with this ID");
            alert.showAndWait();
        }

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
