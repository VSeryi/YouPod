package youpod;

public class Email {
	private String firstname, lastname, subject, email, message;

	public Email(){}
	
	public Email(String firstname, String lastname, String subject,
			String email, String message) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.subject = subject;
		this.email = email;
		this.message = message;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
