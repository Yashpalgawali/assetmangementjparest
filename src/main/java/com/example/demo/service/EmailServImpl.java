package com.example.demo.service;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailserv")
public class EmailServImpl implements EmailService {

	private JavaMailSender mailsend;
	private Environment env;
	
	public EmailServImpl(JavaMailSender mailsend, Environment env) {
		super();
		this.mailsend = mailsend;
		this.env = env;
	}



	@Override
	public void sendSimpleEmail(String toemail, String body, String subject) {
		String from = env.getProperty("spring.mail.username");
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo(toemail);
		message.setFrom(from);
		message.setSubject(subject);
		message.setText(body);
		try {
			mailsend.send(message);
			System.out.println("mail sent success");
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("mail sent failed ");
		}
	}
}
