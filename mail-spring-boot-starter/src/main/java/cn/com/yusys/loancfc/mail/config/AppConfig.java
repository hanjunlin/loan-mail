package cn.com.yusys.loancfc.mail.config;

import cn.com.yusys.loancfc.mail.server.YusysMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author ：hanjl
 * @date ：Created in 2021/11/11 0011 17:29
 * @description：
 */
@Configuration
@Slf4j
public class AppConfig {

    @Bean
    YusysMailSender yusysMailSender(JavaMailSender mailSender){
        log.info("生成--yusysMailSender...");
        return new YusysMailSender(mailSender);
    }
}
