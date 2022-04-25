/*package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
<<<<<<< HEAD
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchBookController implements Initializable {

    @FXML
    private TableView<?> SearchTableView;
=======
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
import java.util.ResourceBundle;
>>>>>>> 28382d25c60e17d41f6d9e9781b854ca435ee395

    public class SearchBookController implements Initializable {

        @FXML
        private TextField filterField;

        @FXML
        private TableColumn<?, ?> AuthorBookCol;

<<<<<<< HEAD
    @FXML
    private TableColumn<?, ?> TitleBookCol;

    @FXML
    private TableColumn<?, ?> LanguageBookCol;

    @FXML
    private TableColumn<?, ?> EditiondateBookCol;
=======
        @FXML
        private TableColumn<?, ?> AvailBookCol;
>>>>>>> 28382d25c60e17d41f6d9e9781b854ca435ee395

        @FXML
        private TableColumn<?, ?> IdBookCol;

        @FXML
        private TableColumn<?, ?> NameBookCol;

        @FXML
        private Button ReturnButton;

        @FXML
        private Button SearchBookButton;

        @FXML
        private TableView<?> table;

        public ObservableList<book> data = FXCollections.observableArrayList();
        @FXML
        void filter(MouseEvent event) {
            String creator = "Victor%20Hugo";
            try {
                String urls = "https://gallica.bnf.fr/SRU?operation=searchRetrieve&version=1.2&query=(dc.creator%20all%20%22"+creator+"%22%20)%20and%20(dc.type%20all%20%22monographie%22)";
                System.out.println(urls);
                URL obj = new URL(urls);
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
                NodeList errNodes = doc.getElementsByTagName("srw:recordData");
                String j="0";
                int i=0;
                while (errNodes.getLength() > 0) {
                    Element err = (Element)errNodes.item(i);
                    i++;
                    book book = new book(j,err.getElementsByTagName("dc:creator").item(0).getTextContent(),err.getElementsByTagName("dc:title").item(0).getTextContent(),err.getElementsByTagName("dc:date").item(0).getTextContent(),err.getElementsByTagName("dc:language").item(0).getTextContent());
                    System.out.println("author : "+err.getElementsByTagName("dc:creator").item(0).getTextContent());
                    System.out.println("title : "+err.getElementsByTagName("dc:title").item(0).getTextContent());
                    System.out.println("date : "+err.getElementsByTagName("dc:date").item(0).getTextContent());
                    System.out.println("language : "+err.getElementsByTagName("dc:language").item(1).getTextContent());
                    data.add(book);
                }
                // success

            } catch (Exception e) {
                System.out.println(e);
            }


        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            id.setCellValueFactory(new PropertyValueFactory<student,String>("ark"));
            title.setCellValueFactory(new PropertyValueFactory<student,String>("title"));
            author.setCellValueFactory(new PropertyValueFactory<student,String>("author"));
            year.setCellValueFactory(new PropertyValueFactory<student,String>("year"));
            language.setCellValueFactory(new PropertyValueFactory<student,String>("language"));



            // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<book> filteredData = new FilteredList<>(data, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(book -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (book.getTitle().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    } else if (book.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches last name.
                    }
                    else if (book.getYear().contains(lowerCaseFilter))
                        return true;
                    else
                        return false; // Does not match.
                });
            });

            // 3. Wrap the FilteredList in a SortedList.
            SortedList<book> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(table.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            table.setItems(sortedData);

        }

    @FXML
    private TextField SearchBookTextField;

    @FXML
    private ChoiceBox<String> FilterBookChoiceBox;

    private String[] options ={"by id","by title","by author","by language","by edition date","by availability"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        FilterBookChoiceBox.getItems().addAll(options);
    }

}
*/