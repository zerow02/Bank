package IIITLBank;

import java.util.Scanner;

public class Login {
    public static int display() {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            Header.head();
            System.out.println("HOW WOULD YOU LIKE TO LOGIN?\n1.THROUGH CARD\n2.CARD LESS");
            choice = sc.nextInt();
            if (choice == 1)
                return 1;
            else if (choice == 2)
                return 2;
            System.out.println("INVALID ENTRY..\nPLEASE TRY AGAIN");
        }
    }

}
