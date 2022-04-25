package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class librarian extends user{
    public librarian(){
        super();
    }
    public void profile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/userProfile.fxml"));
        Parent root = (Parent) loader.load();
        userProfile controller = loader.getController();
        controller.SetUserProfile(getUserID(),getFirst_name(),getLast_name(),getAdress());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void search(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/searchBook.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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

}
