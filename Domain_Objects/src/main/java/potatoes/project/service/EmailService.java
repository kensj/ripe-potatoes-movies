package potatoes.project.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private final String username = "user@gmail.com";
	private final String password = "pass";
	private final Session session;
	
	@Autowired
	public EmailService() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}
	
	@Async
	public void sendVerificationMail(String email, String username, String token) {
		String url = "http://localhost:8080/verify?token=" + token;
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ripepotatoesmovies@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Verify: Ripe Potatoes");
			message.setText("Dear "+username+","
				+ "\n\n Please verify your email using the following link: "
				+ url
				+ "\n\n The link will expire in 24 hours.");
	
			Transport.send(message);
	
		} catch (MessagingException e) {
			System.out.println("ERROR");
			throw new RuntimeException(e);
		}
	}
	
	@Async
	public void sendForgotPassMail(String email, String username, String token) {
		String url = "http://localhost:8080/forgotpassword?token=" + token;
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ripepotatoesmovies@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Forgot Password: Ripe Potatoes");
			message.setText("Dear "+username+","
				+ "\n\n Please change your password using the following link: "
				+ url
				+ "\n\n The link will expire in 24 hours.");
	
			Transport.send(message);
	
		} catch (MessagingException e) {
			System.out.println("ERROR");
			throw new RuntimeException(e);
		}
	}

}
