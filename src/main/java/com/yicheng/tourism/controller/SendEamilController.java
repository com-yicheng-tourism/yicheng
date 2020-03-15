package com.yicheng.tourism.controller;

import com.yicheng.tourism.service.SendEmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

@Api(value = "发送邮件接口",description = "发送邮件接口")
@RestController
@RequestMapping("sendEmail")
public class SendEamilController {
    @Autowired
    private SendEmailService sendEmailService;

    @ApiOperation(value = "查询所有的用户信息")
    @GetMapping("/sendMail")
    public void sendEmail(String receiver) throws GeneralSecurityException, MessagingException {
        boolean flag = true;
        int i = (int)((Math.random()*9+1)*100000);
        String msg = String.valueOf(i);
        flag = sendEmailService.SendEamil(msg,receiver);
        if (flag) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }
}
