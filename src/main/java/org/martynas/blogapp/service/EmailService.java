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
        helper.setSubject("🎉 Welcome to Blog ValueBound, " + username + "! 🎉");

        // Enhanced email content with better formatting and engaging message
        String content = "<div style='font-family: Arial, sans-serif; color: #333;'>"
                       + "<h1 style='color: #2C3E50;'>Hello, " + username + "!</h1>"
                       + "<p style='font-size: 16px;'>"
                       + "Welcome to <strong>Blog ValueBound</strong>! We're thrilled to have you join our community."
                       + "</p>"
                       + "<p style='font-size: 16px;'>"
                       + "Blog ValueBound is where your voice adds value. Whether you're here to share insights, "
                       + "explore new perspectives, or connect with fellow bloggers, this is the place where your contributions matter."
                       + "</p>"
                       + "<p style='font-size: 16px;'>"
                       + "To get started, simply <a href='https://blog-app-java-production.up.railway.app/login' "
                       + "style='color: #3498DB; text-decoration: none;'>log in</a> and begin your journey."
                       + "</p>"
                       + "<p style='text-align: center; margin: 30px 0;'>"
                       + "<a href='https://blog-app-java-production.up.railway.app/login' style='"
                       + "display: inline-block; background-color: #3498DB; color: white; padding: 10px 20px; "
                       + "font-size: 18px; text-decoration: none; border-radius: 5px;'>Log In to Your Account</a>"
                       + "</p>"
                       + "<p style='font-size: 14px; color: #95A5A6;'>"
                       + "If you have any questions or need support, feel free to <a href='mailto:support@valuebound.com' "
                       + "style='color: #3498DB; text-decoration: none;'>contact us</a>."
                       + "</p>"
                       + "<p style='font-size: 14px; color: #95A5A6;'>"
                       + "Happy blogging!<br>The Blog ValueBound Team</p>"
                       + "<p style='text-align: left;'>"
                       + "<img src='cid:logoImage' alt='Blog ValueBound' style='width: 150px; margin-top: 20px;'>"
                       + "</p>"
                       + "</div>";

        helper.setText(content, true);

        // Attach the logo image
        ClassPathResource logo = new ClassPathResource("static/images/val.jpeg");
        helper.addInline("logoImage", logo);

        mailSender.send(message);
    }
}
