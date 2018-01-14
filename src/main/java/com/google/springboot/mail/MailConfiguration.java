package com.google.springboot.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * org-springboot-starter-mail
 *
 * MailSender interface:The Top-level interface that provides basic functionality for sending simple emails
 *      JavaMailSender interface:the sub-interface of the above MailSender,supports MIME messages
 *      JavaMailSenderImpl class:provides an implementation of the JavaMailSender interface.
 */

@Configuration
public class MailConfiguration {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.163.com");
        mailSender.setPort(25);

        mailSender.setUsername("ZW282615SC@163.com");
        mailSender.setPassword("1234SC.ZW");
//        mailSender.setPassword("sjodgcldywuuihed");

        Properties props = new Properties();
        props.put("mail.transport.protocol","smtp");
//        props.put("mail.smtp.auth","true");
//        props.put("mail.smtp.starttls.enable","true");

        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
