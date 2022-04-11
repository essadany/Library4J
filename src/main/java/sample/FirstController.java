package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class FirstController {
    private Stage stage;
    private Scene scene;
    public void isStudent(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/loginStudent.fxml"));
        scene = new Scene(root,600,500);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void isLibrarian(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/loginLibrarian.fxml"));
        scene = new Scene(root,600,500);
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
