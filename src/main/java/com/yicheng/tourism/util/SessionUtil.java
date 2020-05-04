package com.yicheng.tourism.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author
 * @Date
 * @Desc: Http请求工具类 .
 * @Version v1.0.0
 */
@Slf4j
public final class SessionUtil extends HttpServlet {
	/**
	 * 将邮箱验证码放入session
	 * @param msg
	 * @param request
	 * @param response
	 */
	public static void doGet(String name,String msg,HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//使用request对象的getSession()获取session，如果session不存在则创建一个
		HttpSession session = request.getSession();
		//将数据存储到session中
		session.setAttribute(name, msg);
		//获取session的Id
		String sessionId = session.getId();
		//判断session是不是新创建的
		try {
			if (session.isNew()) {
				response.getWriter().print("session创建成功，session的id是："+sessionId);
			}else {
				response.getWriter().print("服务器已经存在该session了，session的id是："+sessionId);
			}
		}catch (Exception e){
			log.info("session出现异常",e);
		}
	}

	public static String getEmailCode(String name,HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		    return 	(String) request.getSession().getAttribute(name);    //从session中获取真正的验证码
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
