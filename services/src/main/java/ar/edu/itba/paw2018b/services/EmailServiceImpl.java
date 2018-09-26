package ar.edu.itba.paw2018b.services;

import ar.edu.itba.paw2018b.interfaces.service.EmailService;
import ar.edu.itba.paw2018b.models.Transaction;
import ar.edu.itba.paw2018b.models.TransactionRequest;
import ar.edu.itba.paw2018b.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    public JavaMailSender jms;

    @Async
    @Override
    public void sendEmail(String recipient, String body, String subject) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(recipient);
        mail.setSubject(subject);
        mail.setText(body);
        jms.send(mail);
    }

    @Override
    public void sendReservationEmail(Transaction transaction, User user, TransactionRequest tr) {
        sendEmail(user.getEmail(),buildEmailString(transaction, user, tr),"Tu compra de entradas");
    }

    private String buildEmailString(Transaction transaction, User user, TransactionRequest tr){
        StringBuilder sb = new StringBuilder();

        sb.append("¡Hola ");
        sb.append(user.getName());
        sb.append("! Aquí están los detalles de tu compra: \n Asientos ");
        for(String s : tr.getSeatNames()){
            sb.append(s);
        }

        sb.append("\n");
        for(String s : tr.getFoodDetails()){
            sb.append(s);
            sb.append("\n");
        }

        sb.append("Total: ");
        sb.append(transaction.getPrice());

        return sb.toString();
    }
}
