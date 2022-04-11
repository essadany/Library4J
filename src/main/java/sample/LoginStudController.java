package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginStudController {
    private Stage stage;
    private Scene scene;
    @FXML
    TextField inputAdress;
    @FXML
    PasswordField inputPwd;
    @FXML
    Label isConnected;
    public void connexion(ActionEvent event) throws IOException, SQLException {
        Connect con = new Connect();
        String sql = "select * from student where adress = ? and password = ? ";
        PreparedStatement stat =con.connection().prepareStatement(sql);
        String adressEntred = inputAdress.getText();
        String pwdEntred = inputPwd.getText();
        stat.setString(1,adressEntred);
        stat.setString(2,pwdEntred);
        ResultSet rs = stat.executeQuery();
        if (rs.next()){
            System.out.println("you are connected");
            Parent root = FXMLLoader.load(getClass().getResource("/student.fxml"));
            scene = new Scene(root, 600, 500);
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else {
            isConnected.setText("adress or password wrong, please try again");
        }

    }

}

