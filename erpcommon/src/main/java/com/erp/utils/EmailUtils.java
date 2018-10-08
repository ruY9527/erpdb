package com.erp.utils;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailUtils
{

    private JavaMailSenderImpl sender;  //srping邮箱类
    //private String from;  //发件人

    public void send(String to,String subject,String text) throws MessagingException
    {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("1411091515@qq.com");
        //收件人
        helper.setTo(to);
        //邮件标题
        helper.setSubject(subject);
        helper.setText(text);
        sender.send(mimeMessage);
    }

    public void setSender(JavaMailSenderImpl sender)
    {
        this.sender = sender;
    }
}
