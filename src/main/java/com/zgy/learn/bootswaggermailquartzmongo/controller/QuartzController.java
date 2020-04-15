package com.zgy.learn.bootswaggermailquartzmongo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.learn.bootswaggermailquartzmongo.service.QuartzService;
import com.zgy.learn.bootswaggermailquartzmongo.util.JSONUtils;
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
    @Autowired
    QuartzService quartzService;

    @RequestMapping(value = "/getNowTime", method = RequestMethod.GET)
    @ResponseBody
    public String getNowTime() throws JsonProcessingException {
        return JSONUtils.getJsonFromObject(quartzService.getTime());
    }
}
