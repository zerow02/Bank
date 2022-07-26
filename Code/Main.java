package IIITLBank;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean pass;
        Card cardobj = new Card();
        User user=new User();
        int loginChoice=Login.display();
        if(loginChoice==2)
        {

String[] hmm={"lakdsf","aksdf"};
 pass=Server.main(hmm);
            Header.head();
            System.out.println("        path : welcome screen\n");
            System.out.println("PROCESSING...PLEASE WAIT...\nCARDLESS LOGIN SUCCESSFUL...\nWELCOME BACK TO THE BANK OF IIITL, "+user.getUserName()+" !!\n");

            if(pass==false) {
    Header.head();
    System.out.println("ERROR..\nYOU HAVE ENTERED INCORRECT OTP..\n");
    main(hmm);
}


        }
else{
        Header.head();
          pass=  cardobj.insertCard(user);}


        if (pass) {
            Password passobj=new Password();
            Balance customer1 = new Balance();
            String anotherTransaction;
                do {
                    String transactionType = Console.readTransactionType();
                    if (transactionType.equals("d")) {
                        boolean exit=DepositScreen.deposit(user);
                        if(!exit)
                            break;

                    }

                    double availableFunds;
                    if (transactionType.equals("w")) {

                         boolean exit=WithdrawalScreen.readWithdrawalAmount(user);
                         if(!exit)
                             break;

                    }
                    if (transactionType.equals("o")) {
                        int choice;
                        boolean out = false;
                        while (true) {
                            System.out.println("ARE YOU SURE YOU WANT TO LOGOUT :( \n1.YES\n2.NO");
                            choice = sc.nextInt();
                            if (choice == 1) {
                                out = true;
                                break;
                            } else if (choice == 2)
                                break;
                        }
                        if (out)
                            break;

                    }
                    if (transactionType.equals("u")) {
boolean found=UpdateUser.UpdateUserInDB(user);
if(found) System.out.println("updated ");
else {
    Header.head();
    System.out.println("		path : management transactions --> update user details --> failed \n");
    System.out.println("FAILED TO UPDATED USER DATA SUCCESSFULLY...PLEASE TRY AGAIN...");

}
                    }
                    if (transactionType.equals("t")) {

                        boolean exit=FundTransfer.realTransferAmount(user);
                        if(!exit)
                            break;
                    }

                    if (transactionType.equals("a")) {
                        boolean exit=AddUser.Viewer(user);
                        if(!exit)
                            break;

                    }

                    if (transactionType.equals("esc")) {
                        break;
                    }

                    if (transactionType.equals("b")) {

                        boolean exit=Balance.viewBalance(user);
                        if(!exit)
                            break;

                }
                    anotherTransaction = Console.readAnotherTransaction();
                } while(!anotherTransaction.equals("n"));


        }

        Console.goodbyeScreen();
    }
}
