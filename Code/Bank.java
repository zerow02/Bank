package IIITLBank;

import com.mysql.cj.protocol.Resultset;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import java.sql.Connection;
import java.sql.DriverManager;

public class Bank{
    private String bankLocation="Bangalore";
   public  boolean verifyCard(int number,User userobj) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String user="root";
            String password="123456";
            String url="jdbc:mysql://localhost:3306/grp_project";
            Connection con =DriverManager.getConnection(url,user,password);
            Statement statement=con.createStatement();
            ResultSet resultset=statement.executeQuery("Select * from cards");
            while(resultset.next())
            {
                if(resultset.getInt("cardNumber")==number)
                {
                     int userID=resultset.getInt("ID");
                    String userName=resultset.getString("Name")	;
                    int userPIN=resultset.getInt("PIN");
                    int userBal=resultset.getInt("acc_bal");
                    String userCity=resultset.getString("CITY");
                    int cardNumber=number;
                    String userMail=resultset.getString("mail")	;
                    userobj=new User(userID,userName,userPIN,userBal,userCity,cardNumber,userMail);
                    FirstMail e2=new FirstMail();
                    return true;

                }

            }
            return false;


        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}
