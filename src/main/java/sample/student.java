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
import java.sql.SQLException;

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
    private Label userID;
    //setScene instance :
    setScene scene = new setScene();

    @FXML
    public void disconnect(ActionEvent event) throws IOException {
        scene.disconnect(event);

    }



    @FXML
    public void searchBook(ActionEvent event) throws IOException {
        scene.setScene(event,"/searchBook.fxml");

    }

    @FXML
    public void userProfile(ActionEvent event) throws IOException, SQLException {
        scene.setScene(event,"/userProfile.fxml");

    }
    @FXML
    public void getBorrowInf(ActionEvent event) throws IOException {
        scene.setScene(event,"/myHistory.fxml");
    }


    public void setUserProfile(int id) {
        userID.setText(String.valueOf(id));
    }
}
