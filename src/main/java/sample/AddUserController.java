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
    private Button cancelButton;
    
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
    void cancel(MouseEvent event) {

    }

    @FXML
    void save(MouseEvent event) {

    }
    @FXML
    void saveAddButton(ActionEvent event){
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
                int id = 55;
                // create our mysql database connection
                // String myDriver = "org.gjt.mm.mysql.Driver";
                String myDriver = "com.mysql.cj.jdbc.Driver";
                String myUrl = "jdbc:mysql://localhost/library";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "");
                Statement stAddUser = conn.createStatement();
                stAddUser.execute("INSERT INTO users VALUES(" +
                        "'" + id + "'," +
                        "'" + firstname + "'," +
                        "'" + lastname + "'," +
                        "'" + adress + "'," +
                        "'" + password + "'," +
                        "'" + role + "');") ;
                id +=1;

            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
        }
            return;
        }

    @FXML
    void cancelAddButton(ActionEvent event){

    }
    public static class JavaMysqlSelect {
    }

}