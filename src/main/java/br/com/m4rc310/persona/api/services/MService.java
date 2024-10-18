package br.com.m4rc310.persona.api.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.m4rc310.persona.api.i18n.IConsts;

public class MService extends br.com.m4rc310.gql.services.MService implements IConsts {

	// Replace FROM with your "From" address.
	// This address must be added to Approved Senders in the console.
	static final String FROM = "admin@m4rc310.com.br";
	static final String FROMNAME = "MLS";

	// Replace TO with a recipient address.
	static final String TO = "marcelo.utfpr@me.com";

	// Replace smtp_username with your Oracle Cloud Infrastructure SMTP username
	// generated in console.
	static final String SMTP_USERNAME = "ocid1.user.oc1..aaaaaaaashr7puf4bwxrxaahi2h5r3r5y7e6h6qxlrqcqhmjlszisiqqu37a@ocid1.tenancy.oc1..aaaaaaaa2r3wyugbbqyjskwnguk5kivle4jxdkt4nno7qfp67rkxl45esk6a.s4.com";

	// Replace smtp_password with your Oracle Cloud Infrastructure SMTP password
	// generated in console.
	static final String SMTP_PASSWORD = "f$YPI<e+uO<8S$h[Iyfr";

	// Oracle Cloud Infrastructure Email Delivery hostname.
	static final String HOST = "smtp.email.sa-saopaulo-1.oci.oraclecloud.com";

	// The port you will connect to on the SMTP endpoint. Port 25 or 587 is allowed.
	static final int PORT = 587;

	static final String SUBJECT = "TEste";
	static final String BODY = String.join(

			System.getProperty("line.separator"), "<h1>OCI Email Delivery test</h1>",
			"<p>This email was sent with OCI Email Delivery using the ",
			"<a href='Javamail'>https://github.com/javaee/javamail'>Javamail Package</a>",
			" for <a href='Javahttps://www.java.com'>Java</a>."

	);

	protected static final String DATE_ISO_MASK = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

	public void sendEmail(String to, String subject, String text) {

		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.auth.login.disable", "true"); // the default authorization order is "LOGIN PLAIN DIGEST-MD5
															// NTLM". 'LOGIN' must be disabled since Email Delivery
															// authorizes as 'PLAIN'
		props.put("mail.smtp.starttls.enable", "true"); // TLSv1.2 is required
		props.put("mail.smtp.starttls.required", "true"); // Oracle Cloud Infrastructure required

		// Create a Session object to represent a mail session with the specified
		// properties.
		Session session = Session.getDefaultInstance(props);
		Transport transport = null;

		try {
			// Create a message with the specified information.
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM, FROMNAME));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
			msg.setSubject(SUBJECT);
			msg.setContent(BODY, "text/html");

			// Create a transport.
			transport = session.getTransport();

			// Send the message.

			System.out.println("Sending Email now...standby...");

			// Connect to OCI Email Delivery using the SMTP credentials specified.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");

		}

		catch (Exception ex) {

			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());

		}

		finally

		{

//			if (transport != null) {
//				transport.close();
//				
//			}

		}

//		Properties props = new Properties();
//		props.put("mail.smtp.host", "localhost");
//		props.put("mail.smtp.port", "25");
//
//		Session session = Session.getInstance(props);
//		MimeMessage message = new MimeMessage(session);
//		try {
//			message.setFrom(new InternetAddress("noreply"));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//			message.setSubject(subject);
//			message.setText(text);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

	}
}
