
package IIITLBank;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class MyEmail {

    /* public static void main(String[] args) {
*/        //authentication info
       public MyEmail(){
           String email;
           final String username = "iiitlbankmailsender@gmail.com";
           final String password = "Lucknow@123";
           String fromEmail = "iiitlbankmailsender@gmail.com";
           email=User.getUserMail();
           String toEmail = email;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
             }
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Transaction Details");
            msg.setText("Your transaction was successful. Your account balance is "+User.getUserBal() +"\n Thank you for banking with us :)"  );

            Transport.send(msg);
            System.out.println("Mail sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}