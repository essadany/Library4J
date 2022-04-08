package sample;

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
            System.out.println("connected not yet!!");

        }

    }
    public Connection connecion(){
        return con;
    }
}
