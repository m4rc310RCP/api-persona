package br.com.m4rc310.persona.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService extends MService {
	@Autowired
    private JavaMailSender emailSender;
	
	public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        
        emailSender.send(message);
	}
}
