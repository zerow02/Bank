package IIITLBank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.*;

public class FundTransfer {

    public FundTransfer() {
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

    public static boolean realTransferAmount(User userobj) {
        int choice ;
        Scanner sc=new Scanner(System.in);

        while(true) {

            //////


            try {
                int pin;
                boolean verified=false;

                for(int i=2;i>=0;i--) {
                    Header.head();
                    System.out.println("        path : management transaction --> fund transfer --> pin verification\n");


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
                    System.out.println("        path : management transaction --> fund transfer\n");


                    ///////////////////////////////
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user="root";
                    String password="123456";
                    String url="jdbc:mysql://localhost:3306/grp_project";
                    Connection con = DriverManager.getConnection(url,user,password);

                    System.out.println("ENTER THE AMOUNT YOU WOULD LIKE TO TRANSFER");
                    int amt=sc.nextInt();
                    boolean continuee=false;
                    while(true) {
                        Header.head();
                        System.out.println("        path : management transaction --> transfer --> amount confirmation\n");
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
                            System.out.println("        path : management transaction --> fund transfer --> enter card number ");
                            System.out.println("ENTER CARD NUMBER OF RECIPIENT: ");
                            int cno=sc.nextInt();
                            String sql1="select * from cards where cardNumber= " +cno;
                            Statement stt=con.createStatement();
                            ResultSet rss=stt.executeQuery(sql1);
                            while(rss.next()) {
                                int id2=rss.getInt("ID");
//        					Statement statement=con.createStatement();
                                Statement statement1 = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
                                ResultSet result1=statement1.executeQuery(sql1);
                                while(result1.next()) {
                                    int acc_bal1=result1.getInt("acc_bal");


//                                    System.out.println("Current balance is: "+acc_bal1);
                                    acc_bal1=acc_bal1+amt;
                                    result1.updateInt("acc_bal", acc_bal1);
                                    result1.updateRow();


Header.head();
                                    System.out.println("        path : management transaction --> fund transfer --> transaction successful\n"   );
                                    System.out.println("TRANSACTION COMPLETE...\nTHE AMOUNT HAS BEEN TRANSFERRED SUCCESSFULLY...\n\n-----------------------------------------------\nTHE CURRENT BALANCE IS :");
                                    System.out.println(acc_bal);
                                    System.out.println("-----------------------------------------------");
                                    resultSet.updateRow();
                                    return true;

                                }
                            }
                            Header.head();

                            System.out.println("        path : management transaction --> fund transfer --> error\n\nERROR!!!\nTHE ACCOUNT YOU ARE TRYING TO TRANSFER YOUR MONEY TO DOESNT EXIST!!!");
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
                    System.out.println("        path : management transaction --> fund transfer --> pin verfication failed\n");
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