package IIITLBank;

import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    static Connection con;

    public static Connection CreateC() {

           try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String user="root";
            String password="123456";
            String url="jdbc:mysql://localhost:3306/grp_project";
           Connection con =DriverManager.getConnection(url,user,password);
                Statement statement=con.createStatement();
ResultSet resultset=statement.executeQuery("Select * from cards");



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
           return con;
    }
}
