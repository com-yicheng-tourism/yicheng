package com.yicheng.tourism.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface VerCodeService {

    /**
     * 获取验证码图片
     * @param request
     * @param response
     */
     void getVerCode(HttpServletRequest request, HttpServletResponse response);

    /**
     * 从session中获取验证码文字
     * @param request
     * @return
     */
    String getVerCodeText(HttpServletRequest request);
}
