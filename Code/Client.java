package IIITLBank;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket Soc = new Socket("LocalHost",3478);

            BufferedReader br= new BufferedReader(new InputStreamReader(Soc.getInputStream()));

            Header.head();

            System.out.print(br.readLine());
            System.out.println(" IS THE OTP FOR YOUR LOGIN TO THE ATM BANK OF IIITL\nOTPs ARE SECRET.\nDO NOT DISCLOSE THIS TO ANYONE.BANK OF IIITL NEVER ASKS FOR YOUR OTP\n ");
            Soc.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
