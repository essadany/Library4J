package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MyHistory {
    @FXML
    private TableColumn<?, ?> AuthorBookCol;

    @FXML
    private TableColumn<?, ?> AvailBookCol;

    @FXML
    private TableColumn<?, ?> IdBookCol;

    @FXML
    private TableColumn<?, ?> NameBookCol;

    @FXML
    private Button backButtonProfile;

    @FXML
    private ChoiceBox<?> checkBoxBorrowing;

    @FXML
    private TextField searchBorrowingHistory;

    @FXML
    private Button searchButtonBorrowing;

    @FXML
    private TableView<?> tableBorrowingUser;
}
