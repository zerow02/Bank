package IIITLBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.*;

public class WithdrawalScreen {

    public WithdrawalScreen() {
    }

    public static boolean isValid(double withdrawal, double available) {
        return withdrawal <= available;
    }

    public static void insufficientFunds(User userobj) {

        Header.head();
        System.out.println("        path : management transaction --> withdrawal --> transaction failed\n");
        System.out.println("INSUFFICIENT FUNDS TO CARRY OUT THE TRANSANCTION!!");
        System.out.println("AVAILABLE ACCOUNT BALANCE: " + userobj.getUserBal());
    }

    public static boolean readWithdrawalAmount(User userobj) {
        int choice ;
        Scanner sc=new Scanner(System.in);

        while(true) {

            //////


            try {
                int pin;
                boolean verified=false;

                for(int i=2;i>=0;i--) {
                    Header.head();
                    System.out.println("        path : management transaction --> withdrawal --> pin verification\n");


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
                    System.out.println("        path : management transaction --> withdrawal\n");


                    ///////////////////////////////
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user="root";
                    String password="123456";
                    String url="jdbc:mysql://localhost:3306/grp_project";
                    Connection con = DriverManager.getConnection(url,user,password);

                    System.out.println("ENTER THE AMOUNT YOU WOULD LIKE TO WITHDRAW");
                    int amt=sc.nextInt();
                    boolean continuee=false;
                    while(true) {
                        Header.head();
                        System.out.println("        path : management transaction --> withdrawal --> amount confirmation\n");
                        System.out.println("YOU HAVE ENTERED ₹" + amt + "\nDO YOU WISH TO PROCEED\n1.YES (type 1)\n2.NO, LET ME RE-ENTER THE AMOUNT (type 2)");
                        choice = sc.nextInt();

                        if (choice == 1) {
                            continuee=true;
                            break;
                        }

                        if(choice==2)
                            break;





                        System.out.println("WRONG CHOICE ENTERED!!\nPLEASE TRY AGAIN");
                    }
                    if(!continuee) continue;








                    String sql="select * from cards  where PIN="+userobj.getUserPIN();

                    Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
                    ResultSet resultSet=statement.executeQuery(sql);
                    while(resultSet.next()) {
                        int acc_bal=resultSet.getInt("acc_bal");
                        if(acc_bal>amt) {

                            acc_bal=acc_bal-amt;userobj.setUserBal(acc_bal);
                            resultSet.updateInt("acc_bal", acc_bal);
                            System.out.println("PROCESSING...PLEASE WAIT PATIENTLY... ");
                            Header.head();
                            System.out.println("        path : management transaction --> withdrawal --> transaction successful\n"   );
                            System.out.println("TRANSACTION COMPLETE...\nPLEASE COLLECT THE CASH...\n\n-----------------------------------------------\nTHE CURRENT BALANCE IS :");
                            System.out.println(acc_bal);
                            System.out.println("SENDING RECEIPT THROUGH MAIL...");
                            System.out.println("-----------------------------------------------");
                            MyEmail e1=new MyEmail();
                            resultSet.updateRow();
                            return true;

                        }
                        else if(acc_bal<amt) {
                            insufficientFunds(userobj);
                        }


                    }
                }
                else
                {
                    Header.head();
                    System.out.println("        path : management transaction --> withdrawal --> pin verfication failed\n");
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