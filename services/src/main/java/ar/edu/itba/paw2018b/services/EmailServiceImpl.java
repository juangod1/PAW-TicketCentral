package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    public JavaMailSender jms;

    @Override
    public void sendEmail(String recipient, String body, String subject) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(recipient);
        mail.setSubject(subject);
        mail.setText(body);
        jms.send(mail);
    }
}
