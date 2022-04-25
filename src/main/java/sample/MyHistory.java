package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MyHistory implements Initializable {

    @FXML
    private TableView<?> tableBorrowingUser;

    @FXML
    private TableColumn<?, ?> AuthorHistoryCol;

    @FXML
    private TableColumn<?, ?> DateRenderHistoryCol;

    @FXML
    private TableColumn<?, ?> IdHistoryCol;

    @FXML
    private TableColumn<?, ?> LanguageHistoryCol;

    @FXML
    private TableColumn<?, ?> TitleHistoryCol;

    @FXML
    private TableColumn<?, ?> EditiondateHistoryCol;

    @FXML
    private Button ReturnButton;

    @FXML
    private TextField searchBorrowingHistory;

    @FXML
    private Button searchButtonBorrowing;

    @FXML
    private ChoiceBox<String> HistoryChoiceBox;

    private String[] options ={"by id","by title","by author","by language","by date"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

        HistoryChoiceBox.getItems().addAll(options);
    }



}

