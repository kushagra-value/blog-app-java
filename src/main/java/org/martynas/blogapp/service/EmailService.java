package org.martynas.blogapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String to, String username) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Welcome to Blog ValueBound!");

        // Load email template with the logo and login link
        String content = "<h1>Welcome to Blog ValueBound, " + username + "!</h1>"
                       + "<p>Thank you for signing up. We're glad to have you with us.</p>"
                       + "<p>To login, please <a href='http://localhost:9080/login'>click here</a>.</p>"
                       + "<p><img src='cid:logoImage' alt='Blog ValueBound'></p>";

        helper.setText(content, true);

        // Attach the logo image
        ClassPathResource logo = new ClassPathResource("static/images/val.jpeg");
        helper.addInline("logoImage", logo);

        mailSender.send(message);
    }
}
