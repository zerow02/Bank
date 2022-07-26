package IIITLBank;

import java.util.Scanner;

public class Password {

    public Password() {
    }
    public boolean verifyPin(User user, int entered,int attempt)
    {
        if(entered==user.getUserPIN())
        {
            Header.head();
            System.out.println("VERIFYING PIN...PLEASE WAIT PATIENTLY!!\nPIN VERIFICAIION SUCCESSFUL!!");
            return  true;
        }
        else
        {
            Header.head();
            System.out.println("VERIFYING PIN...PLEASE WAIT PATIENTLY!!\nINCORRECT PIN!!");
            if (attempt!= 0) {
                System.out.println("YOU HAVE " + attempt+ " MORE ATTEMPTS LEFT!!");
                System.out.println(" ");
                System.out.println("WARNING:IF THE TOTAL NUMBER OF ATTEMPTS EXCEEDS 3, THE ACCOUNT WILL BE LOCKED FOR 24 HOURS");
            }
            return  false;
        }

    }


    public  boolean EnterPassword(User user) {
        int passwordStored=user.getUserPIN();
        Scanner scanner = new Scanner(System.in);
        int passwordEntry = 0;

        for(int i = 0; i < 3; ++i) {
            Header.head();
            System.out.println(user.getUserName()+", PLEASE ENTER YOUR 4 DIGITS PIN :");
            passwordEntry = scanner.nextShort();
            if (passwordEntry == passwordStored) {
                break;
            }

            Header.head();
            System.out.println("VERIFYING PIN...PLEASE WAIT PATIENTLY!!\nINCORRECT PIN!!");
            if (i != 2) {
                System.out.println("YOU HAVE " + (3 - i - 1) + " MORE ATTEMPTS LEFT!!");
                System.out.println(" ");
                System.out.println("WARNING:IF THE TOTAL NUMBER OF ATTEMPTS EXCEEDS 3, THE ACCOUNT WILL BE LOCKED FOR 24 HOURS");
            }
        }

        if (passwordEntry !=passwordStored ) {
            System.out.println("WARNING!!");
            System.out.println("TOTAL NUMBER OF ATTEMPTS EXCEEDED");
            System.out.println("YOUR ACCOUNT IS LOCKED FOR 24 HOURS");
            return false;
        } else {
            Header.head();
            System.out.println("VERIFYING PIN... PLEASE WAIT PATIENTLY...\nPIN VERIFICATION SUCCESSFUL");
            System.out.println("SUCCESSFULLY LOGGED IN!!");
            return true;
        }
    }
}
