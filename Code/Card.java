package IIITLBank;

import java.util.Scanner;

public class Card {
    private int cardnumber;

    public Card() {
    }

    public boolean insertCard(User user) {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("PLEASE INSERT YOUR CARD HERE (enter the 8-digit card number) : ");
            this.cardnumber = sc.nextInt();
            Bank obj=new Bank();

            if (obj.verifyCard(this.cardnumber,user)) {
                Header.head();
                System.out.println("        path : welcome screen\n");
                System.out.println("PROCESSING...PLEASE WAIT...\nCARD READ SUCCESSFULLY!!\nWELCOME BACK TO THE BANK OF IIITL, "+user.getUserName()+" !!\n");
                return true;
            }

            Header.head();
            System.out.println("PROCESSING...PLEASE WAIT...\nUNABLE TO READ THE CARD!!");
            System.out.println("PLEASE CHECK IF THE INSERTED CARD IS IIITL BANK's!!");
            System.out.println("  ");
            System.out.println("-------------------------------------------------------------------------");
        }
    }
}
