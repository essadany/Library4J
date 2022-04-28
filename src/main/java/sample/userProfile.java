package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

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

    @FXML
    void disconnect(ActionEvent event) throws IOException {
        user.disconnect(event);
    }

    @FXML
    void profileBack(ActionEvent event) {

    }
    public void SetUserProfile(int u,String f,String l,String a){
        userID.setText(String.valueOf(u));
        adress.setText(a);
        first_name.setText(f);
        last_name.setText(l);
        adress.setText(a);
    }

}
