package crystalcrusies;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class MailClass {    
    String 	d_email = "baskar10576@gmail.com",
            d_password = "#sbi10576", //your email password
            d_host = "smtp.gmail.com",
            d_port = "465",
            m_to = "baskar10576@gmail.com", // Target email address
            m_subject = "Testing",
            m_text = "Hey, this is a test email.";
    
    public MailClass() {
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        try {
            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);     
            
            //Define message
            MimeMessage msg = new MimeMessage(session);
          //  msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(d_email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
             
         /*   // Define message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("baskar10576@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("baskar10576@gmail.com"));
            message.setSubject("Hello JavaMail Attachment");*/

            // create the message part 
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            //fill message
            messageBodyPart.setText("Hi");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource("F:/TestLeaf - ProjectWork/TestData.xls");
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("F:/TestLeaf - ProjectWork/TestData.xls");
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            msg.setContent(multipart);

            // Send the message
            Transport.send( msg );
            
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
   
    public static void main(String args[]){
    MailClass mc = new MailClass();
    }
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(d_email, d_password);
        }
    }
}