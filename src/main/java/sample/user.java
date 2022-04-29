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
    private  String role;
    private   String password;
    private  String adress;
    private  String first_name;
    private  String last_name;
    private  int userID;
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

    @FXML
    private TextField  inputAdress;
    @FXML
    PasswordField inputPwd;
    @FXML
    Label isConnected;
    Connect con = new Connect();
    setScene scene= new setScene();
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
            String path;

            //Show user interface
            if (getRole().equals("student")){
                path="/student.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Parent root = loader.load();
                student controller = loader.getController();
                controller.setUserProfile(rs.getInt(0));

            } else {
                path="/librarian.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                Parent root = loader.load();
                librarian controller = loader.getController();
                controller.setUserProfile(rs.getInt(0));
            }
            scene.setScene(event,path);
        }else {
            isConnected.setText("adress or password wrong, please try again");
        }




    }

    //method search to show new Scene to search a book
    public void searchBook(ActionEvent event) throws IOException {
        scene.setScene(event,"/searchBook.fxml");
    }

}

