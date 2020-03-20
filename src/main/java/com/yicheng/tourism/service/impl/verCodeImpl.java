package com.yicheng.tourism.service.impl;

import com.yicheng.tourism.service.VerCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@Service
public class verCodeImpl implements VerCodeService {
    @Override
    public void getVerCode(HttpServletRequest request, HttpServletResponse response) {
         try {
             /*
             1.生成验证码
             2.把验证码上的文本存在session中
             3.把验证码图片发送给客户端
             */
             ImageVerificationService ivc = new ImageVerificationService();     //用我们的验证码类，生成验证码类对象
             BufferedImage image = ivc.getImage();  //获取验证码
             request.getSession().setAttribute("text", ivc.getText()); //将验证码的文本存在session中
             ivc.output(image, response.getOutputStream());//将验证码图片响应给客户端
         }catch (IOException e){
             log.info("获取验证码出错",e);
         }
    }

    /**
     * 从session中获取验证码文字
     *
     * @param request
     * @return
     */
    @Override
    public String getVerCodeText(HttpServletRequest request) {
        String codeText=null;
        try {
            request.setCharacterEncoding("utf-8");
            codeText=(String) request.getSession().getAttribute("text");    //从session中获取真正的验证码
        }catch (IOException e){
            log.info("从session中获取验证么对应的文字失败",e);
        }

        return codeText;
    }
}
