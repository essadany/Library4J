package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class librarian extends user{
    public librarian(){
        super();
    }
    user user = new user();
    @FXML
    private Button borrowingLibButton;

    @FXML
    private Button disconnectButton;

    @FXML
    private Button manageUsersButton;

    @FXML
    private Button searchButton2;

    @FXML
    private Button userProfileButton;
    @FXML
    public void disconnect(ActionEvent event) throws IOException {
        scene.disconnect(event);

    }

    @FXML
    public void getBorrowInf(ActionEvent event) throws IOException {
        scene.setScene(event,"/manageLonas.fxml");

    }

    @FXML
    public void manageUser(ActionEvent event) throws IOException {
        scene.setScene(event,"/manageUsers.fxml");

    }

    @FXML
    public void searchBook(ActionEvent event) throws IOException {
        scene.setScene(event,"/searchBook.fxml");

    }

    @FXML
    public void userProfile(ActionEvent event) throws IOException, SQLException {
        scene.setScene(event,"/userProfile.fxml");

    }




}
