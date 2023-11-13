package com.FoneBook.services;


import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServices {

    public boolean sendEmail(String subject, String message, String to) {

        boolean isEmailSent = false;
        //Add your from email
        String from = "1MS21CS071@msrit.edu";
        String host = "smtp.gmail.com";
       // replace with your Gmail password

        // Set mail properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //Add your password
                return new PasswordAuthentication("1ms21cs071@msrit.edu", "");
            }
        });
        session.setDebug(true);
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);


            System.out.println("Email sent successfully!");
            isEmailSent = true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return isEmailSent;
    }
}
