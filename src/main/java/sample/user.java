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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {
    //attributs
    private static String role;
    private  static String password;
    private static String adress;
    private static String first_name;
    private static String last_name;
    private static int userID;
    //constroctor
    public user(String role, String password, String adress, String first_name, String last_name, int userID) {
        this.role = role;
        this.password = password;
        this.adress = adress;
        this.first_name = first_name;
        this.last_name = last_name;
        this.userID = userID;
    }

    public user() {
        super();
    }

    //getters

    public String getRole() {
        return this.role;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAdress() {
        return this.adress;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public int getUserID() {
        return this.userID;
    }
    //Setters

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    //method authentificate
    private Stage stage;
    private Scene scene;
    @FXML
    TextField inputAdress;
    @FXML
    PasswordField inputPwd;
    @FXML
    Label isConnected;
    Connect con = new Connect();
    public void authentificate(ActionEvent event) throws IOException, SQLException {
        String sql = "select * from users where adress = ? and password = ? ";
        PreparedStatement stat = con.connection().prepareStatement(sql);
        String adressEntred = inputAdress.getText();
        String pwdEntred = inputPwd.getText();
        stat.setString(1, adressEntred);
        stat.setString(2, pwdEntred);
        ResultSet rs = stat.executeQuery();
        //if the informations of user are corrects
        if (rs.next()) {
            //Set user informations
            setUserID(rs.getInt("userID"));
            setAdress(adressEntred);
            setRole(rs.getString("role"));
            setPassword(pwdEntred);
            setFirst_name(rs.getString("first_name"));
            setLast_name(rs.getString("last_name"));
            //Show user interface
            String path;
            if (getRole().equals("student")){
                path="/student.fxml";
            } else {
                path="/librarian.fxml";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent root = (Parent) loader.load();
            scene = new Scene(root, 600, 500);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }else {
            isConnected.setText("adress or password wrong, please try again");
        }



    }
    //method search to show new Scene to search a book
    public void search(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/searchBook.fxml"));
        Parent root = (Parent) loader.load();
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

