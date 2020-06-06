package com.jide.emailservice.service.impl;

import com.jide.emailservice.entity.Mail;
import com.jide.emailservice.kafka.receiver.events.User;
import com.jide.emailservice.repository.MailRepository;
import com.jide.emailservice.service.EmailService;
import com.jide.emailservice.service.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    public EmailSender emailSender;

    @Autowired
    public MailRepository mailRepository;

    @Override
    public void sendSimpleMessage(User input) {

            Mail newMail = new Mail();
            newMail.setTo(input.getUsername());
            newMail.setSubject("TestSubject");
            newMail.setText("TestText");

            String message =
                    "Hello" + input.getUsername() + ",\n" +
                            "You just created an account on QueuePay\n" +
                            "You are Welcome.";

            mailRepository.save(newMail);
            emailSender.sendEmail(input.getUsername(), "welcome to my platform", message);


    }
}

