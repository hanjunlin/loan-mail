package cn.com.yusys.loan.mail.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/*
1.获取发送的原文
2.利用系统的随机数产生对称密钥
3.计算原文的摘要
4.用自己的私钥对摘要进行加密（签名）
5.用对称密钥对（原文和签名）进行加密
6.用对方的公钥对称密钥进行加密（加密密钥）
7.将密文（5）和加密密钥（6）一起发给对方
 */
@SpringBootApplication
@Slf4j
public class MailApplication implements CommandLineRunner {

    @Resource
    private ConfigurableEnvironment springEnv;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MailApplication.class);
        springApplication.addListeners((ApplicationListener<ApplicationContextInitializedEvent>) event -> log.info("====== AppEnv set context ======"));
        springApplication.run(args);
    }

    @Override
    public void run(String... args) {
        MutablePropertySources propSrcs = springEnv.getPropertySources();
        // 启动参数
        @SuppressWarnings("rawtypes")
        Map<String, String> props = StreamSupport.stream(propSrcs.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames()).flatMap(Arrays::stream).distinct()
                .collect(Collectors.toMap(Function.identity(), springEnv::getProperty));

        int interval = 20;
        int max = props.keySet().stream().max(Comparator.comparingInt(String::length)).orElse("").length();
        StringBuilder msg = new StringBuilder("启动参数：\n" + String.join("", Collections.nCopies(150, "=")) + "\n");
        for (String k : props.keySet()) {
            int i = max - k.length() + interval;
            String join = String.join("", Collections.nCopies(i, " "));
            msg.append(String.format("%s%s%s", k, join, props.get(k))).append("\n");
        }
        msg.append(String.join("", Collections.nCopies(150, "=")));
        log.info(msg.toString());
        log.info("正常启动！");
    }
}
