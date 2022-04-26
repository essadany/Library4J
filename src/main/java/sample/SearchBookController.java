package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchBookController implements Initializable {

    @FXML
    private TableView<?> SearchTableView;

    @FXML
    private TableColumn<?, ?> AuthorBookCol;

    @FXML
    private TableColumn<?, ?> AvailBookCol;

    @FXML
    private TableColumn<?, ?> IdBookCol;

    @FXML
    private TableColumn<?, ?> TitleBookCol;

    @FXML
    private TableColumn<?, ?> LanguageBookCol;

    @FXML
    private TableColumn<?, ?> EditiondateBookCol;

    @FXML
    private Button ReturnButton;

    @FXML
    private Button SearchBookButton;

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
