package research.data;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class GMailSender extends javax.mail.Authenticator {
	
	public static void emailRecommendTrigger(String name, String senderMail,  String friendMail, String messageTxt){
		final String username = "register.uncc@gmail.com";
		final String password = "registeration1";
		String[] to = { friendMail };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress(senderMail);
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("Recommendation");
			message.setText("Hi "+ friendMail.split("\\@")[0]  + ",\n" 
					+ "\nThis mail is a  recommendation from Research Participant Group."
					+ "\nYou have been recommended for "+name+".\n\n"
					+"Message:  "+messageTxt
					+ "\n\nRegards,\n" + senderMail + "");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	public static void emailContactTrigger(String name, String friendMail, String messageTxt){
		final String username = "register.uncc@gmail.com";
		final String password = "registeration1";
		String[] to = { friendMail };
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			 InternetAddress me = new InternetAddress("studentreguncc@gmail.com");
		        try {
					me.setPersonal(name);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        message.setFrom(me);
			for (int i = 0; i < to.length; i++) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[i]));
			}
			message.setSubject("Contact");
			message.setText("Hi "+ friendMail.split("\\@")[0]  + ",\n" 
					+ "\nThis mail is a  contact from Research Participant Group."
					+ "\nYou have been added as a contact for "+name+".\n\n"
					+"Message:  "+messageTxt
					+ "\n\nRegards,\n" + "Group-Admin");

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}