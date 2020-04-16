package com.zgy.learn.bootswaggermailquartzmongo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.bootswaggermailquartzmongo.service.QuartzService;
import com.zgy.learn.bootswaggermailquartzmongo.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: renjiaxin
 * @Despcription:
 * @Date: Created in 2020/4/15 22:11
 * @Modified by:
 */

@Controller
@RequestMapping("/job")
public class QuartzController {
    Logger log = LoggerFactory.getLogger(QuartzController.class);
    @Autowired
    QuartzService quartzService;

    @RequestMapping(value = "/getNowTime", method = RequestMethod.GET)
    @ResponseBody
    public String getNowTime() throws JsonProcessingException {
        try{
            return JSONUtils.getJsonFromObject(quartzService.getTime());
        }catch (Exception e){
            log.error("获取时间错误，{}",e.getStackTrace());
            return "获取时间错误";
        }
    }

    @RequestMapping(value = "/sendMailFixed", method = RequestMethod.GET)
    @ResponseBody
    public String sendMailFixed() throws JsonProcessingException {
        try{
            return JSONUtils.getJsonFromObject(quartzService.sendMailFixedTime());
        }catch (Exception e){
            log.error("发送邮件错误，{}",e.getStackTrace());
            return "发送邮件错误";
        }
    }
}
