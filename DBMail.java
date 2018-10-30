import java.sql.*;
import java.util.logging.*;
import java.io.*;
import java.util.Properties; 
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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
import javax.mail.*; 
import javax.sql.*;
import javax.mail.internet.*; 
import javax.activation.FileDataSource;
public class DBMail{
    public static void main(String args[]) {
        String dbURL = "jdbc:mysql://localhost:3306/hrsalaryslipontime";
        String username ="root";
        String password = "123123";
      
        Connection dbCon = null;
        Statement stmt = null;
        ResultSet rs = null; 
        String query;
        try {
			File folder = new File("PDFs");
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					query="select  emailId from  hrsalaryslipontime where empId=\""+listOfFiles[i].getName().substring(0, 6)+"\";";
					dbCon = DriverManager.getConnection(dbURL, username, password);
					stmt = dbCon.prepareStatement(query);
					rs = stmt.executeQuery(query);
					while(rs.next()){
						String to=rs.getString(1);
						String from="enablepshr@gmail.com";//change accordingly 
						String gpassword="abcd@1234";//change accordingly 
						Properties props = new Properties(); 
						props.put("mail.smtp.host", "smtp.gmail.com"); 
						props.put("mail.smtp.socketFactory.port", "465"); 
						props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); 
						props.put("mail.smtp.auth", "true"); 
						props.put("mail.smtp.port", "465"); 

						Session session = Session.getDefaultInstance(props, 
						new javax.mail.Authenticator() { 
							protected PasswordAuthentication getPasswordAuthentication() { 
								return new PasswordAuthentication(from,gpassword);
							} 
						});
						try { 
							MimeMessage message = new MimeMessage(session); 
							message.setFrom(new InternetAddress(from)); 
							message.addRecipient(Message.RecipientType.TO,new InternetAddress(to)); 
							message.setSubject("Nothing Special.."); 
							BodyPart messageBodyPart = new MimeBodyPart();
							messageBodyPart.setText("This is message body");
							Multipart multipart = new MimeMultipart();
							multipart.addBodyPart(messageBodyPart);
							messageBodyPart = new MimeBodyPart();
							String filename = "PDFs\\"+listOfFiles[i].getName();
							DataSource source = new FileDataSource(filename);
							messageBodyPart.setDataHandler(new DataHandler(source));
							messageBodyPart.setFileName(filename);
							multipart.addBodyPart(messageBodyPart);
							message.setContent(multipart);
							Transport.send(message); 
							System.out.println("message sent successfully"); 
						} catch (MessagingException e) {
							throw new RuntimeException(e);
						}System.out.println(rs.getString(1));
					}
				}
			}
        } catch (SQLException ex) {
			System.out.println(ex);
        }
    }  
}