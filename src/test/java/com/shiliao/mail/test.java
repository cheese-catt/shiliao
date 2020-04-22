package com.shiliao.mail;

import com.shiliao.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private MailService mailService;

    private static final String TO = "2813062251@qq.com";
    private static final String SUBJECT = "主题 - 测试邮件";
    private static final String CONTENT = "Testing Testing Testing";


    /**
     * 测试发送html邮件
     */
    @Test
    public void sendHtmlMessage() {
        String htmlStr = "<a href='http://localhost:8080/pages/index.html' >点我发财</a>";
        mailService.sendMimeMessge(TO, SUBJECT, htmlStr);
    }


}

