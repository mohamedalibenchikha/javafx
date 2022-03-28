/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import devit.workit.entites.Evenement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Nadia
 */
public class Mails {
    
   public static void sendMail1( String emailTo, String object, String mssg ) {
       
       System.out.println("Preparing to send email ..");
 
        final String user = "noreplay.espritwork@gmail.com";//change accordingly  
        final String password = "esprit12";//change accordingly  
        
//        Evenement e = new Evenement();
//        String emailTo = e.getEmail();

        String to =  emailTo;//change accordingly 
        
// Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Get the session object  
 

        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        //Compose the message  
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(object);
            message.setText(mssg);

            //send the message  
            Transport.send(message);

            System.out.println("message sent successfully...");
        } catch (AddressException ex) {
            Logger.getLogger(Mails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Mails.class.getName()).log(Level.SEVERE, null, ex);
   

    }
}
    
}
