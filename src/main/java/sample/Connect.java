package sample;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    Connection con;

    public Connect(){
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost/library?ZoneTime=UTC","root","");
            System.out.println("connected!!");
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please connect to database server !");
            alert.showAndWait();

        }

    }
    public Connection connection(){
        return con;
    }
}
