package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.sql.ClientInfoStatus;
import java.sql.*;

public class AddUserController {

    @FXML
    private TextField adress_tf;


    
    @FXML
    private TextField password_tf;

    @FXML
    private TextField firstname_tf;

    @FXML
    private TextField lastname_tf;

    @FXML
    private TextField role_tf;

    @FXML
    private Button saveButton;

    @FXML
    void saveAddButton(ActionEvent event){
        Connect conn = new Connect();
        String firstname = firstname_tf.getText();
        String lastname = lastname_tf.getText();
        String role = role_tf.getText();
        String adress = adress_tf.getText();
        String password = password_tf.getText();

        Boolean warning = firstname.isEmpty() || lastname.isEmpty() || role.isEmpty() || adress.isEmpty() || password.isEmpty();
        if (warning == true){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("One or more fields are empty");
            alert.showAndWait();
        }
        else {

            try
            {


                PreparedStatement stAddUser = conn.connection().prepareStatement("INSERT INTO users VALUES(default,?,?,?,?,?)");
                stAddUser.setString(1,firstname);
                stAddUser.setString(2,lastname);
                stAddUser.setString(3,adress);
                stAddUser.setString(4,password);
                stAddUser.setString(5,role);
                stAddUser.execute();

            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("User added succefuly!");
            alert.showAndWait();
        }

    }


}