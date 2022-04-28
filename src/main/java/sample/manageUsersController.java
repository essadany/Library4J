package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class manageUsersController {
    setScene scene = new setScene();
    @FXML
    public void backScene(ActionEvent event) throws IOException {
        scene.setScene(event,"/librarian.fxml");
    }
    @FXML
    public void disconnect(ActionEvent event) throws IOException {
        scene.disconnect(event);
    }
}
