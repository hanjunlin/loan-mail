package cn.com.yusys.loan.mail.boot;

import cn.com.yusys.loancfc.mail.dto.MailEntity;
import cn.com.yusys.loancfc.mail.server.YusysMailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author ：hanjl
 * @date ：Created in 2021/11/11 0011 18:47
 * @description：
 */
@SpringBootTest(classes = {MailApplication.class})
@RunWith(SpringRunner.class)
public class AppTest {

    @Autowired
    private YusysMailSender yusysMailSender;

    @Test
    public void test(){
        MailEntity mailEntity = new MailEntity();
        mailEntity.setFrom("hanjl@yusys.com.cn");
        mailEntity.setSubject("hello");
        mailEntity.setText("hello");
        mailEntity.setTo(Arrays.asList("beijing3721@foxmail.com"));
        mailEntity.setCc(Arrays.asList("beijing3721@foxmail.com"));
        mailEntity.setBcc(Arrays.asList("beijing3721@foxmail.com"));
        yusysMailSender.send(mailEntity);
    }
}
