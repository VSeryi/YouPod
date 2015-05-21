package youpod;

	import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

	@Entity
	public class EmailContestacion extends Email {
		public EmailContestacion(String firstname, String lastname, String subject,
				String email, String message){
		super( firstname, lastname, subject,email, message);
		}
		public EmailContestacion(Email email){
			super(email);
	}
	}
