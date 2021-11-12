package cn.com.yusys.loancfc.mail.server;

import cn.com.yusys.loancfc.mail.dto.MailEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author ：hanjl
 * @date ：Created in 2021/11/11 0011 17:53
 * @description：
 */
public class YusysMailSender {
    private JavaMailSender mailSender;

    public YusysMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(MailEntity entity){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(entity.getFrom());
        String str="";
        for(String to:entity.getTo()){
            if("".equals(str)){
                str = to;
            }else{
                str = str + "," + to;
            }
        }
        message.setTo(str);
        message.setSubject(entity.getSubject());
        message.setText(entity.getText());

        str="";
        for(String cc:entity.getCc()){
            if("".equals(str)){
                str = cc;
            }else{
                str = str + "," + cc;
            }
        }
        message.setCc(str);

        str="";
        for(String bcc:entity.getBcc()){
            if("".equals(str)){
                str = bcc;
            }else{
                str = str + "," + bcc;
            }
        }
        message.setBcc(str);
        mailSender.send(message);
    }
}
