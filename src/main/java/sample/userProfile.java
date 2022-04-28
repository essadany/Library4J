package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class userProfile {
    user user = new user();

    //SetUser Profile
    @FXML
    private Text adress;

    @FXML
    private Button backButtonProfile;

    @FXML
    private Button disconnectButtonProfile;

    @FXML
    private Text first_name;

    @FXML
    private Text last_name;

    @FXML
    private Text role;

    @FXML
    private Text userID;
    //instance of setScene class
    setScene scene = new setScene();
    @FXML
    void disconnect(ActionEvent event) throws IOException {
        scene.disconnect(event);

    }

    @FXML
    public void profileBack(ActionEvent event) throws IOException {
        String path;
        if (role.getText().equals("student")){
            path="/student.fxml";
        }else {
            path="/librarian.fxml";
        }
        scene.setScene(event,path);
    }

    public void SetUserProfile(String a) throws SQLException {
        String sql  = "select * from users";
        Connect con = new Connect();
        PreparedStatement stat = con.connection().prepareStatement(sql);
        //userID.setText(String.valueOf(u));
        adress.setText(a);
        /*first_name.setText(f);
        last_name.setText(l);
        adress.setText(a);*/
    }

}
