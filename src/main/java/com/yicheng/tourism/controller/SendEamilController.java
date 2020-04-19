package com.yicheng.tourism.controller;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.service.SendEmailService;
import com.yicheng.tourism.util.SessionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.GeneralSecurityException;

@Api(value = "发送邮件接口",description = "发送邮件接口")
@RestController
@Slf4j
public class SendEamilController {
    @Autowired
    private SendEmailService sendEmailService;

    @ApiOperation(value = "发送验证码")
    @GetMapping("/sendMail")
    public BaseResponse<String> sendEmail(String receiver, HttpServletRequest request, HttpServletResponse response){
        boolean flag = true;
        int i = (int)((Math.random()*9+1)*100000);
        String msg = String.valueOf(i);
        SessionUtil.doGet(receiver,msg,request,response);
        try {
            flag = sendEmailService.SendEamil(msg,receiver);
            if (flag) {
                return new BaseResponse<>("发送成功");
            } else {
                return new BaseResponse<>("发送失败");
            }
        }catch (Exception e){
            log.info("发送邮件出错",e);
            return new BaseResponse<>("发送失败");

        }
    }
    @ApiOperation(value = "发送验证码")
    @GetMapping("/sendMail/msg")
    public BaseResponse<String> sendMailMessage(String receiver, HttpServletRequest request, HttpServletResponse response){
        boolean flag = true;
        String msg="您已成功注册驿程旅游网站,请立即登录查看,是否本人操作,谢谢";
        try {
            flag = sendEmailService.SendEamil(msg,receiver);
            if (flag) {
                return new BaseResponse<>("发送成功");
            } else {
                return new BaseResponse<>("发送失败");
            }
        }catch (Exception e){
            log.info("发送邮件出错",e);
            return new BaseResponse<>("发送失败");

        }
    }
}
