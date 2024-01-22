package com.maksimbalashov.spring.service;

import com.maksimbalashov.spring.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService{
    private final JavaMailSender mailSender;
    @Value("${server.port}")
    private int port;
    @Value("${server.hostname}")
    private String hostname;

    @Override
    public void sendActivateCode(User user) {
        String subject = "please activate your account";
        String content = "Please activate your account, go to the link: \n"
                + "https://" + hostname + ":" + port + "/users/activate/" + user.getActiveCode();
        sendMail(user.getEmail(),subject,content);
    }

    private void sendMail(String email, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("test@balashov.pro");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
