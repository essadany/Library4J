package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SearchBookController implements Initializable {
    setScene scene = new setScene();

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
        table.getItems().clear();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<book, String>("ark"));
        title.setCellValueFactory(new PropertyValueFactory<book, String>("title"));
        author.setCellValueFactory(new PropertyValueFactory<book, String>("author"));
        date.setCellValueFactory(new PropertyValueFactory<book, String>("year"));
        language.setCellValueFactory(new PropertyValueFactory<book, String>("language"));
        status.setCellValueFactory(new PropertyValueFactory<book, String>("status"));

        table.setItems(data);
        choiceBook.setItems(list);

    }
    @FXML
    void backInterface(ActionEvent event) throws IOException {
        scene.disconnect(event);
    }
}