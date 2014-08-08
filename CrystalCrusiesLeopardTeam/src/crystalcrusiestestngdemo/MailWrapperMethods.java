package crystalcrusiestestngdemo;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class MailWrapperMethods {
	
	public static String Username;
	public static String Password;
	public static String Host;
	public static String Port;
	public static String To;
	public static String Cc;
	public static String Subject;
	public static String Body;
	
	public void SendMail(){
		Properties props = new Properties();
	    props.put("mail.smtp.user", Username);
	    props.put("mail.smtp.host", Host);
	    props.put("mail.smtp.port", Port);
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.socketFactory.port", Port);
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    
	    try {
	    	Authenticator auth = new SMTPAuthenticator();
	        Session session = Session.getInstance(props, auth);     
	        
	        //Define message
	        MimeMessage msg = new MimeMessage(session);
	        msg.setSubject(Subject);
	        msg.setFrom(new InternetAddress(Username));
	        String[] add = To.split(",");
	        
	        for(int i=0;i<add.length;i++){
	        	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(add[i]));
	        }
	        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(Cc.toString()));
	      
	        // create the message part 
	        MimeBodyPart messageBodyPart = new MimeBodyPart();

	        //fill message
	        messageBodyPart.setText(Body);

	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);

	        // Part two is attachment
	        messageBodyPart = new MimeBodyPart();
	        DataSource source = new FileDataSource(CrystalCrusiesWrapperMethods.ReportPath + "_" + CrystalCrusiesWrapperMethods.testName + "_" + CrystalCrusiesWrapperMethods.date3 + ".xls");
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(CrystalCrusiesWrapperMethods.ReportPath + "_" + CrystalCrusiesWrapperMethods.testName + "_" + CrystalCrusiesWrapperMethods.date3 + ".xls");
	        multipart.addBodyPart(messageBodyPart);

	        // Put parts in message
	        msg.setContent(multipart);

	        // Send the message
	        Transport.send( msg );
	        
	    } catch (Exception mex) {
	        mex.printStackTrace();
	    }		
	}		
	
	private class SMTPAuthenticator extends javax.mail.Authenticator {
	        public PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(Username, Password);
	        }
	    }
	
	public void MailParameter() throws BiffException, IOException, RowsExceededException, WriteException{
		
		Workbook wb = Workbook.getWorkbook(new File("F:\\TestLeaf - ProjectWork\\TestData.xls"));
		Sheet sh = wb.getSheet("Email Configuration");
		int conRow = sh.getRows();
		for(int m=1;m<conRow;m++){
		Cell cell = sh.getCell(0, m);
		String name = cell.getContents();
		if(name.equalsIgnoreCase("Username")){
			Username = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("Password")){
			Password = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("Host")){
			Host = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("Port")){
			Port = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("To")){
			To = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("Cc")){
			Cc = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("Subject")){
			Subject = sh.getCell(1, m).getContents();
		}
		else if(name.equalsIgnoreCase("Body")){
			Body = sh.getCell(1, m).getContents();
		}
	  }
	}	
}
