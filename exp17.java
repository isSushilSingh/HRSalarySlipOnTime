package advancejava;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*; 
public class exp17{
    public static void main(String[] args){
     	String host = "smtp.gmail.com"; 
	String user = "advancejavaprogram@gmail.com"; 
	String pass = "hello123123"; 
	String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	String from1 = "advancejavaprogram@gmail.com";
	String to1 = "prabalraghav74@gmail.com"; 
	String subject1 = "java";
	String messageText = "Hello hw r u?";
	Properties props = System.getProperties();
        props.put("mail.host", host);
	props.put("mail.transport.protocol.", "smtp");
	props.put("mail.smtp.port", "465");
	props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
	Session mailSession = Session.getDefaultInstance(props, null);
	Message msg = new MimeMessage(mailSession);
        try{
            InternetAddress  fr = new InternetAddress(from1);
            msg.setFrom(fr);
            InternetAddress[] address = {new InternetAddress(to1)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject1);
            msg.setContent(messageText, "text/html");
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Mail Sent");
        }catch (Exception err){
            System.out.println("Mail not sent");
            System.out.println(err);				
        }
    }
}