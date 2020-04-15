package com.zgy.learn.bootswaggermailquartzmongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: renjiaxin
 * @Despcription: 定时任务
 * @Date: Created in 2020/4/15 22:01
 * @Modified by:
 */
@Service
public class QuartzService {
    private String timeFormatter = "yyyy-MM-dd HH:mm:ss";
    @Autowired
    MailService mailService;

    @Scheduled(cron = "0 * * * * ?") //每一分钟都打印一次时间
    public String getTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        return nowTime;

    }

    @Scheduled(cron = "0 */3 * * * ?") //每3分钟发送一份邮件
    // @Scheduled(cron = "0 * * * * ?") //每一分钟都打印一次时间
    public String sendMailFixedTime() {
        String to = "renjiaxin@126.com";
        String subject = "ni好";
        String content = "11111";

        mailService.sendMailWithoutAppendix(to, subject, content);
        return "发送成功!";
    }
}
