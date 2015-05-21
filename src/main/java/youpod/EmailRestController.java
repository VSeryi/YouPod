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
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Autowired
	private RegistroRepository emailRegistroRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Email> addUser(@RequestBody Email email) {
		String mensaje = "Hola " + email.getFirstname() + " "+ email.getLastname()+",\nHemos recibido el siguiente mensaje:\n" + email.getMessage()+ "\nTe responderemos con la mayor brevedad posible";
		if (revenEmail(email.getEmail(), email.getSubject(), mensaje)) {
			System.out.println("Okis");
			EmailContestacion emilio = new EmailContestacion (email);
			emailRepository.save(emilio);
			return new ResponseEntity<>(email, HttpStatus.OK);
		}
		else {
			System.out.println("No okis");
			return new ResponseEntity<>(email, HttpStatus.NOT_ACCEPTABLE);
		}
	} 
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<Email> addUser2(@RequestBody Email email) {
		String mensaje = "Hemos completado su registro con exito, recuerde que su email es: "+email.getEmail();
		String subject = "Registro correcto";
		if (revenEmail(email.getEmail(), subject, mensaje)) {
			System.out.println("Ok");
			EmailRegistro emili = new EmailRegistro (email);
			emailRegistroRepository.save(emili);
			return new ResponseEntity<>(email, HttpStatus.OK);
		}
		else {
			System.out.println("No ok");
			return new ResponseEntity<>(email, HttpStatus.NOT_ACCEPTABLE);
		}
	} 
	
	private boolean revenEmail(String email, String asunto, String mensaje) {
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
	                                   new InternetAddress(email));
	          message.addRecipient(Message.RecipientType.BCC,
                     new InternetAddress(username));

	          // Set Subject: header field
	          message.setSubject(asunto);

	          // Now set the actual message
	          message.setText(mensaje);
	          // Send message
	          Transport.send(message);
	       }catch (MessagingException mex) {
	          mex.printStackTrace();
	          return false;
	       }
		return true;
		}
	}
	



