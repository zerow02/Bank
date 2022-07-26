package IIITLBank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DepositScreen {


    public static void exceeds(User userobj) {

        Header.head();
        System.out.println("        path : management transaction --> withdrawal --> transaction failed\n");
        System.out.println("YOU ARE TRYING TO DEPOSIT A LARGE AMOUNT OF MONEY!!\nPLEASE VISIT ONE OF OUR BRANCHES TO MAKE A BIG DEPOSIT\n");
        System.out.println("AVAILABLE ACCOUNT BALANCE: " + userobj.getUserBal());
    }


    public static Scanner scanner;

    public DepositScreen() {
    }


    public static double readDepositAmount() {
        while(true) {
            Header.head();
            System.out.println("        path : management transaction --> deposit\n");

            System.out.println("PLEASE ENTER THE AMOUNT YOU WOULD LIKE TO DEPOSIT (in ₹):");
            double deposit = scanner.nextDouble();
            System.out.println("PLEASE DEPOSIT THE CASH BELOW..");
            System.out.println("(you deposited the cash)");
            System.out.println("PROCESSING...PLEASE WAIT...");
            if (deposit > 0.0D && deposit <= 20000.0D) {
                return deposit;
            }

            System.out.println("TRANSACTION CANCELLED");
            Header.head();
            System.out.println("        path : management transaction --> deposit --> deposition failed\n");

            System.out.println("WARNING: YOU CAN DEPOSIT UPTO ₹20000 ONLY!!\nIF YOU WANT TO DEPOSIT MORE, THEN PLEASE VISIT OUR NEAREST BRANCH AND MAKE THE DEPOSITION!!");

            System.out.println("PLEASE COLLECT YOUR CASH!!");


        }
    }

    static {
        scanner = new Scanner(System.in);
    }



    public static boolean deposit(User userobj) {
        int choice ;
        Scanner sc=new Scanner(System.in);

        while(true) {

            //////


            try {
                int pin;
                boolean verified=false;

                for(int i=2;i>=0;i--) {
                    Header.head();
                    System.out.println("        path : management transaction --> deposit --> pin verification\n");


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
                    System.out.println("        path : management transaction --> deposit\n");


                    ///////////////////////////////
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String user="root";
                    String password="123456";
                    String url="jdbc:mysql://localhost:3306/grp_project";
                    Connection con = DriverManager.getConnection(url,user,password);

                    System.out.println("ENTER THE AMOUNT YOU WOULD LIKE TO DEPOSIT");
                    int amt=sc.nextInt();
                    boolean continuee=false;
                    while(true) {
                        Header.head();
                        System.out.println("        path : management transaction --> deposit --> amount confirmation\n");
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
                        if(amt+acc_bal<=100000) {


                            acc_bal=acc_bal+amt;userobj.setUserBal(acc_bal);
                            resultSet.updateInt("acc_bal", acc_bal);
                            System.out.println("(you deposited the amount)\nPROCESSING...PLEASE WAIT PATIENTLY... ");
                            Header.head();
                            System.out.println("        path : management transaction --> deposition --> transaction successful\n"   );
                            System.out.println("TRANSACTION COMPLETE...\n\n-----------------------------------------------\nTHE CURRENT BALANCE IS :");
                            System.out.println(acc_bal);
                            System.out.println("-----------------------------------------------");
                            MyEmail e1=new MyEmail();
                            resultSet.updateRow();

                            return true;

                        }
                        else  {
exceeds(userobj);
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
