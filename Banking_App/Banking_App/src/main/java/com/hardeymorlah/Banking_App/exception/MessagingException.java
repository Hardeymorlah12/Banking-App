package com.hardeymorlah.Banking_App.exception;

import org.springframework.mail.MailException;

public class MessagingException extends Exception {
    public MessagingException(String msg) {
        super(msg);
    }

    public MessagingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
