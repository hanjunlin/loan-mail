package cn.com.yusys.loancfc.mail.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：hanjl
 * @date ：Created in 2021/11/11 0011 17:16
 * @description：
 */
@Data
public class MailEntity {
    private String subject;
    private String from;
    private List<String> to = new ArrayList<>();
    private List<String> cc = new ArrayList<>();
    private List<String> bcc = new ArrayList<>();
    private Date sendDate = new Date();
    private String text;
}
