package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class setScene {
    private Stage stage;
    private Scene scene;
    public void setScene(ActionEvent event,String path) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1000,700);
        stage.setScene(scene);
        stage.show();
    }
    public void disconnect(ActionEvent event) throws IOException {
        setScene(event,"/user.fxml");
    }

}
