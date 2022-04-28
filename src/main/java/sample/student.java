package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class student extends user {
    public student(){
        super();
    }
    user user = new user();
    @FXML
    private Button borrowing;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button searchButton2;

    @FXML
    private Button userProfile;

    @FXML
    public void disconnect(ActionEvent event) throws IOException {
        user.disconnect(event);

    }



    @FXML
    public void searchBook(ActionEvent event) throws IOException {
        user.searchBook(event);

    }

    @FXML
    public void userProfile(ActionEvent event) throws IOException {
        user.userProfile(event);

    }
    @FXML
    public void getBorrowInf(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/myHistory.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
