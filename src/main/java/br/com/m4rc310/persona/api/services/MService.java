package br.com.m4rc310.persona.api.services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.m4rc310.persona.api.i18n.IConsts;

public class MService extends br.com.m4rc310.gql.services.MService implements IConsts {
	protected static final String DATE_ISO_MASK = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
	
	
	
	public void sendEmail(String to, String subject, String text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "localhost");
		props.put("mail.smtp.port", "25"); 
		
		 Session session = Session.getInstance(props);
		 MimeMessage message = new MimeMessage(session);
		 try {
	            message.setFrom(new InternetAddress("noreply"));
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            message.setSubject(subject);
	            message.setText(text);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	}
}
