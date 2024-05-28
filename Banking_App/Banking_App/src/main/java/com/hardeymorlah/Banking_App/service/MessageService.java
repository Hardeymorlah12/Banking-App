package com.hardeymorlah.Banking_App.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final JavaMailSender javaMailSender;

    public MessageService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;

    }

    @Async
    public void loginNotification( String receiver, String message ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Login Notification");
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());



    }

    @Async
    public void registrationNotification( String receiver, String firstName ) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(receiver);
        messageHelper.setSubject("Registration Successful!");
        String message = String.format("Dear %s,\nCongratulations!\nYou have successfully registered with Email address: %s", firstName, receiver);
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());

    }

    @Async
    public void depositNotification(String firstName, String username, double amount) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(username);
        messageHelper.setSubject("Fund Deposit Alert!");
        String message = String.format("Dear %s,\nA deposit of %s has been credited into your account number.", firstName, amount);
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());
    }

    @Async
    public void withdrawalNotification(String firstName, String username, double amount) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(username);
        messageHelper.setSubject("Fund Debit Alert!");
        String message = String.format("Dear %s,\nA withdrawal of %s has been debited from your account number.", firstName, amount);
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());
    }
    @Async

    public void sendResetCode(String username, String code) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        messageHelper.setTo(username);
        messageHelper.setSubject("Password Reset Code!");
        String message = String.format("Dear %s,\nHere is your password reset code: %s.", username, code);
        messageHelper.setText(message);

        javaMailSender.send(messageHelper.getMimeMessage());
    }
}
       /*
        String[] addresses = new String[]{"sruffy07@gmail.com", "skytecomputer@gmail.com", receiver};
        List<String> addressList = Arrays.asList(addresses);
        addressList.forEach(add -> {
            AccountUser user = accountUserService.getAccountUserByUsername(add).getBody();
            assert user != null;
            String text = "Dear "+ user.getFirstName() +"\n" +
                    "There has been a successful login into your Banking Account. Please if you did not log in" +
                    " call us on the following number: 01-2245845, 08004455454\n"
                    + "Thank you for Banking with Us.";
            try{
                messageHelper.setTo(add);
                messageHelper.setText(text);
                javaMailSender.send(messageHelper.getMimeMessage());
            }catch (MessagingException messagingException){
                System.out.println(messagingException.getMessage());
            }
        });
        */

