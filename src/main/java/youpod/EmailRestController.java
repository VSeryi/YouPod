package youpod;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/email")

public class EmailRestController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Email> addUser(@RequestBody Email email) {
	      String from = "support@youpod.es";	      
	      final String username = "youpod.email@gmail.com";
			final String password = "youpoddaw";
	 
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
	 
			Session session = Session.getInstance(properties,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });
	      try{
	          // Create a default MimeMessage object.
	          MimeMessage message = new MimeMessage(session);

	          // Set From: header field of the header.
	          message.setFrom(new InternetAddress(from));

	          // Set To: header field of the header.
	          message.addRecipient(Message.RecipientType.TO,
	                                   new InternetAddress(email.getEmail()));

	          // Set Subject: header field
	          message.setSubject(email.getSubject());

	          // Now set the actual message
	          message.setText(email.getMessage());

	          // Send message
	          Transport.send(message);
	          System.out.println("Sent message successfully....");
	       }catch (MessagingException mex) {
	          mex.printStackTrace();
	       }
		return new ResponseEntity<>(email, HttpStatus.CREATED);
		}}
	
	

