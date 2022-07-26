package IIITLBank;

import java.util.Scanner;

public class Console {
    public static Scanner scanner;

    public Console() {
    }

    public static String readTransactionType() {
        while(true) {
            Header.head();
            System.out.println("        path : management transaction\n");
            System.out.println("SELECT MANAGEMENT TRANSACTION :");
            System.out.println("1.WITHDRAWAL (type w)");
            System.out.println("2.DEPOSIT (type d)");
            System.out.println("3.CHECK ACCOUNT BALANCE (type b)");
            System.out.println("4.FUND TRANSFER (type t)");
            System.out.println("5.UPDATE USER DATA(tyep u)");
            System.out.println("6.ADD A NEW USER(type a)");

            System.out.println("7.LOG OUT (type o)");

//            System.out.println("\npress esc to cancel transaction (type esc)\n");
            System.out.println("WAITING FOR INPUT...");
            String transactionType = scanner.next();
            if (transactionType.equals("w") ||transactionType.equals("a")||transactionType.equals("t")||transactionType.equals("u")||transactionType.equals("o")|| transactionType.equals("d") || transactionType.equals("b")) {
                return transactionType;
            }

            Header.head();
            System.out.println("WRONG ENTRY!!\n");
            System.out.print("PLEASE ENTER 'w' for WITHDRAWAL, 'd' FOR DEPOSIT , 'b' TO CHECK ACCOUNT BALANCE");
        }
    }

    public static String readAnotherTransaction() {
        while(true) {
            Header.head();
            System.out.print("Would you like to undertake another transaction? (type y/n) :");
            String anotherTransaction = scanner.next();
            if (anotherTransaction.equals("y") || anotherTransaction.equals("n")) {
                return anotherTransaction;
            }

            System.out.println("Please enter 'y' for another transaction or 'n' to log out :");
        }
    }

    public static void goodbyeScreen() {
        Header.head();
        System.out.print("THANK YOU FOR BANKING WITH THE BANK OF IIITL \nVISIT BANK OF IIITL AGAIN !!\nADIOS AMIGO\n");
        System.out.println("-------------------------------------------------------------------------\n");
    }

    static {
        scanner = new Scanner(System.in);
    }
}
