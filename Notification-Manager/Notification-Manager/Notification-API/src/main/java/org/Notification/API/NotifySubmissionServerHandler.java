package org.Notification.API;

import org.Notification.Sender.MailNotification;
import org.apache.thrift.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class NotifySubmissionServerHandler implements NotifySubmissionService.Iface {

	@Override
	public String notifySubmission(String requestID) throws TException {
		(new MailNotification()).sendMail(requestID, (new NotifySubmissionServerHandler()).getAdminId());
		return "Request processed" + requestID;
	}

	/*private void sendMail(String requestId, String adminId) {

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "demosga123@gmail.com";//
		final String password = "sgauser123";
		try {
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress("xxxx@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(adminId, false));
			msg.setSubject("Request Made");
			msg.setText("There is request made with id" + requestId);
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Message sent.");
		} catch (MessagingException e) {
			System.out.println("Erreur d'envoi, cause: " + e);
		}

	}*/

	private String getAdminId() {
		return "demosga123@gmail.com";
	}
}
