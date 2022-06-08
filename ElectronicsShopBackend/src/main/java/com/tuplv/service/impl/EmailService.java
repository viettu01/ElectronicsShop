package com.tuplv.service.impl;

import com.tuplv.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSenderImpl;

    @Override
    public void sendEmail(String email, String fullName) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Bạn đã đặt hàng thành công");
        simpleMailMessage.setText("Xin chào, " + fullName
                + "\n\tĐơn hàng của bạn đã được xác nhân"
                + "\nXin trân trọng cảm ơn!");

        javaMailSenderImpl.send(simpleMailMessage);
    }
}
