package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MyHistory {
    setScene scene = new setScene();
    @FXML
    public void backScene(ActionEvent event) throws IOException {
        scene.setScene(event,"/student.fxml");
    }
    @FXML
    public void disconnect(ActionEvent event) throws IOException {
        scene.disconnect(event);
    }




}

