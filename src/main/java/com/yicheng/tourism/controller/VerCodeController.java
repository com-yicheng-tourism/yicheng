package com.yicheng.tourism.controller;


import com.yicheng.tourism.service.VerCodeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 登陆的controller
 */
@Api(value = "验证码服务接口",description = "验证码服务接口")
@Slf4j
@Controller
@RequestMapping("/verCode")
public class VerCodeController {

    @Autowired
    private VerCodeService verCodeService;

    /**
     * 在控制器中把图片响应给前端页面
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/getVerCode",method = RequestMethod.GET)
    @ResponseBody
    public void getVerCode(HttpServletRequest request, HttpServletResponse response){
       verCodeService.getVerCode(request,response);
    }

    /**
     * 从session获得验证码字符
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/authentication",method = RequestMethod.GET)
    @ResponseBody
    public String authentication(HttpServletRequest request){
        return verCodeService.getVerCodeText(request);
    }
}
