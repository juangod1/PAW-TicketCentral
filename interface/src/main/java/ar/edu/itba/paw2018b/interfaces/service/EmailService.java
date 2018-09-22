package ar.edu.itba.paw2018b.interfaces.service;

public interface EmailService {
    public void sendEmail(String recipient, String body, String subject);
}
