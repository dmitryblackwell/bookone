package com.bookshelf.util;

import org.apache.log4j.Logger;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

    private static final Logger LOGGER = Logger.getLogger(MailUtil.class);

    private MailUtil() {}

    private static final String FROM = "no_reply@bookone.com";
    private static final String HOST = "smtp.gmail.com";

    public static void sendMail(final String emailAddress, final String subject, final String mailText) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "127.0.0.1");
        props.put("mail.smtp.port", "2109");
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM));
        message.setRecipient(RecipientType.TO, new InternetAddress(emailAddress));
        message.setSubject(subject);
        message.setText(mailText, "UTF-8"); // as "text/plain"
        message.setSentDate(new Date());
        Transport.send(message);
    }
}
