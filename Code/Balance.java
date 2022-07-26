package IIITLBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Balance {


    public static boolean viewBalance(User userobj) {
        int choice ;
        Scanner sc=new Scanner(System.in);

        while(true) {

            //////


            try {
                int pin;
                boolean verified=false;

                for(int i=2;i>=0;i--) {
                    Header.head();
                    System.out.println("        path : management transaction --> view balance --> pin verification\n");


                    System.out.println("PLEASE ENTER THE PIN TO PROCEED\n(type -1 to abort transaction)");
                    pin = sc.nextInt();
                    if(pin==-1) return true;

                    Password password=new Password();
                    if(password.verifyPin(userobj,pin,i))
                    {
                        verified=true;
                        break;
                    }

                }

                if(verified) {
                    Header.head();
                    System.out.println("        path : management transaction --> view balance\n");


                    ///////////////////////////////
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user="root";
                    String password="123456";
                    String url="jdbc:mysql://localhost:3306/grp_project";
                    Connection con = DriverManager.getConnection(url,user,password);

//
                    String sql="select * from cards  where PIN="+userobj.getUserPIN();

                    Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
                    ResultSet resultSet=statement.executeQuery(sql);
                    while(resultSet.next()) {
                        int acc_bal=resultSet.getInt("acc_bal");


                            resultSet.updateInt("acc_bal", acc_bal);
                            System.out.println("PROCESSING...PLEASE WAIT PATIENTLY... ");
                            Header.head();
                            System.out.println("        path : management transaction --> view balance --> operation successful\n"   );
                            System.out.println("\n-----------------------------------------------\nTHE CURRENT BALANCE IS (in ₹)  :");
                            System.out.println(userobj.getUserBal());
                        System.out.println("-----------------------------------------------");

                            return true;




                    }
                }
                else
                {
                    Header.head();
                    System.out.println("        path : management transaction --> view balance --> pin verfication failed\n");
                    System.out.println("WARNING!!");
                    System.out.println("TOTAL NUMBER OF ATTEMPTS EXCEEDED");
                    System.out.println("YOUR ACCOUNT IS LOCKED FOR 24 HOURS");
                    return  false;
                }



            }
            catch(Exception e) {
                e.printStackTrace();
            }



            /////



        }
    }
}
