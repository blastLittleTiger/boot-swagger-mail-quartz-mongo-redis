package com.zgy.learn.bootswaggermailquartzmongoredis.controller;

import com.zgy.learn.bootswaggermailquartzmongoredis.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: renjiaxin
 * @Despcription: 使用springboot发送邮件
 * @Date: Created in 2020/4/15 21:53
 * @Modified by:
 */
@Controller
public class MailController {
    @Autowired
    MailService mailService;

    @GetMapping("sendmail")
    @ResponseBody
    public String sendSimpleMail(String to, String subject, String content) {
        if (null == to || null == subject || null == subject){
            return "不允许为空";
        }
        mailService.sendMailWithoutAppendix(to, subject, content);
        return "发送成功!";
    }
}
