package com.zgy.learn.bootswaggermailquartzmongo.service;

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

    @Scheduled(cron = "0 * * * * ?") //每一分钟都打印一次时间
    public void printTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(DateTimeFormatter.ofPattern(timeFormatter)).toString();
        System.out.println("nowTime: " + nowTime);

    }
}
