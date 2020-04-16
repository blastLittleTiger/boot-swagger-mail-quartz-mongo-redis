package com.zgy.learn.bootswaggermailquartzmongo.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Slf4j
@Service
public class QuartzService {
    //Logger log = LoggerFactory.getLogger(QuartzService.class);
    private String timeFormatter = "yyyy-MM-dd HH:mm:ss";
    @Autowired
    MailService mailService;

    @Scheduled(cron = "0 * * * * ?") //每一分钟都打印一次时间
    public String getTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        log.info("print now time : {}", nowTime);
        return nowTime;

    }

    @Scheduled(cron = "0 */3 * * * ?") //每3分钟发送一份邮件
    // @Scheduled(cron = "0 * * * * ?") //每一分钟都打印一次时间
    public String sendMailFixedTime() {
        String to = "renjiaxin@126.com";
        String subject = "ni好";
        String content = "11111";
        mailService.sendMailWithoutAppendix(to, subject, content);
        log.info("邮件发送成功！{}" + LocalDateTime.now().toString());
        return "发送成功!";
    }
}
