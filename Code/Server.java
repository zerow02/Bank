package IIITLBank;
import javax.xml.xpath.XPath;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Server {
    static public int OneTimePassword(){
        Random num= new Random();
        int otp = num.nextInt(5000);
        otp=otp+5000;
        return otp;
    }

    public static boolean main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int otpEntered, cardNumber;
        int otp = OneTimePassword();
        boolean correct = false;
        User userobj = new User();
        Bank bank = new Bank();
        Header.head();
        System.out.println("            path : cardless login ");
        System.out.println("PLEASE TYPE YOUR CARD NUMBER TO PROCEED LOGGING IN CARDLESS:");
        cardNumber = sc.nextInt();
        if (bank.verifyCard(cardNumber, userobj))
        {
            try {
                Header.head();
                System.out.println("            path : cardless login -> sending otp");
                System.out.println("SENDING OTP TO YOUR REGISTERED PHONE NUMBER...PLEASE WAIT...\n");
                ServerSocket ss = new ServerSocket(3478);
                Socket Soc = ss.accept();
                ;
                Header.head();
                System.out.println("            path : cardless login -> otp sent successfully");
                System.out.println("OTP SENT SUCCESSFULLY TO YOUR REGISTERED PHONE NUMBER\n\nPLEASE ENTER THE SENT OTP:");

                PrintStream ps = new PrintStream(Soc.getOutputStream());
                ps.print(otp);

                Soc.close();
                ss.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        otpEntered = sc.nextInt();
        if (otp == otpEntered)
            correct = true;
        return correct;
    }
        else
        {
            Header.head();
            System.out.println("YOU HAVE ENTERED A WRONG CARD NUMBER...PLEASE TRY AGAIN...");
            return false;

        }
    }

}
