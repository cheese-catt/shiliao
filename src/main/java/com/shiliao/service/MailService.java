package com.shiliao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


    @Service
    public class MailService {
        private static final Logger logger = LoggerFactory.getLogger(com.shiliao.service.MailService.class);

        @Autowired
        private JavaMailSender mailSender;

        private static final String SENDER = "744900446@qq.com";


        /**
         * 发送 HTML 邮件
         *
         * @param to      收件人
         * @param subject 主题
         * @param content 内容
         */
        public void sendMimeMessge(String to, String subject, String content) {
            MimeMessage message = mailSender.createMimeMessage();
            try {
                //true表示需要创建一个multipart message
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(SENDER);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(content, true);
                mailSender.send(message);
            } catch (MessagingException e) {
                logger.error("发送MimeMessge时发生异常！", e);
            }
        }

    }


