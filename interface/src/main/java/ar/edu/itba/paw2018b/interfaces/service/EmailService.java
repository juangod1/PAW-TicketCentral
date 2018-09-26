package ar.edu.itba.paw2018b.interfaces.service;

import ar.edu.itba.paw2018b.models.Transaction;
import ar.edu.itba.paw2018b.models.TransactionRequest;
import ar.edu.itba.paw2018b.models.User;

public interface EmailService {
    public void sendEmail(String recipient, String body, String subject);
    public void sendReservationEmail(Transaction transaction, User user, TransactionRequest tr);
}
