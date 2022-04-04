package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {

    public static void main(String[] args) {
// write your code here
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        Scene scene = new Scene(root,600,400);
        primaryStage.setTitle("Library4J");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
