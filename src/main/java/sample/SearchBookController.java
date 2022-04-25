package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;

public class SearchBookController {

    @FXML
    private TableColumn<?, ?> AuthorBookCol;

    @FXML
    private TableColumn<?, ?> AvailBookCol;

    @FXML
    private TableColumn<?, ?> IdBookCol;

    @FXML
    private TableColumn<?, ?> NameBookCol;

    @FXML
    private Button ReturnBook;

    @FXML
    private ImageView ReturnButton;

    @FXML
    private Button SearchBookButton;

}