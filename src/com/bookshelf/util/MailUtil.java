package com.bookshelf.util;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

public class MailUtil {

    private static final Logger LOGGER = Logger.getLogger(MailUtil.class);


    public void sendMail(final String emailAddress, final String subject, final String mailText) throws MessagingException, EmailException {
        Email email = new SimpleEmail();
        // Configuration
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(Constant.LOGIN, Constant.PASS));

        // Required for gmail
        email.setSSLOnConnect(true);

        // Sender
        email.setFrom(Constant.FROM_ADDRESS);

        // Email title
        email.setSubject(subject);

        // Email message.
        email.setMsg(mailText);

        // Receiver
        email.addTo(emailAddress);
        email.send();
        LOGGER.info("Email was sent to " + emailAddress);
    }
}
