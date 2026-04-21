package com.erp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 邮件发送工具类
 *
 * 注意：需要配置 spring.mail 相关属性才能启用邮件发送功能
 * 如果未配置，邮件发送功能将被禁用
 */
@Component
/**
 * Email工具类
 *
 * @author baoyang
 */
public class EmailUtils {

    private static final Logger log = LoggerFactory.getLogger(EmailUtils.class);

    /**
 * 邮件发送器（可选注入）
     * 如果未配置邮件服务，此 bean 不会创建，邮件功能禁用
 */
    @Autowired(required = false)
    private JavaMailSender mailSender;

    /**
 * 发送简单邮件
 *
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
 */
    public void send(String to, String subject, String content) {
        if (mailSender == null) {
            log.warn("邮件服务未配置，无法发送邮件。请在 application.yml 中配置 spring.mail 属性");
            log.info("邮件内容 - 收件人: {}, 主题: {}, 内容: {}", to, subject, content);
            return;
        }
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
            log.info("邮件发送成功 - 收件人: {}", to);
        } catch (Exception e) {
            log.error("邮件发送失败: {}", e.getMessage(), e);
        }
    }

    /**
 * 检查邮件服务是否可用
 *
     * @return true-可用, false-不可用
 */
    public boolean isAvailable() {
        return mailSender != null;
    }
}