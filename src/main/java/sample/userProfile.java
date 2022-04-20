package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class userProfile {
    //SetUser Profile
    @FXML
    private Label adress;
    @FXML
    private Label userID;
    @FXML
    private Label first_name;
    @FXML
    private Label last_name;
    @FXML
    public void SetUserProfile(int u,String f,String l,String a){
        userID.setText(String.valueOf(u));
        adress.setText(a);
        first_name.setText(f);
        last_name.setText(l);
    }
}
