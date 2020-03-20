package com.yicheng.tourism.service.impl;

import com.yicheng.tourism.service.SendEmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;



@Service
public class SendEmailServiceImpl  implements SendEmailService {

    private static final Logger logger = LogManager.getLogger(SendEmailServiceImpl.class);

    @Override
    public boolean SendEamil(String msg,String receiver) throws GeneralSecurityException, MessagingException {
        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");

        //获取properties中的属性
        Properties prop = new Properties();
        InputStream in = new SendEmailServiceImpl().getClass().getResourceAsStream("/application.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            logger.error("读取配置文件异常",e);
        }
        String user = prop.getProperty("email.host");
        String password = prop.getProperty("email.password");
        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,password);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com",user,password);

        //创建邮件对象
        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(user));

        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(receiver));

        //邮件标题
        mimeMessage.setSubject("Hello Mail");

        //邮件内容
        mimeMessage.setContent(msg,"text/html;charset=UTF-8");

        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

        try {
            //关闭连接
            transport.close();
        } catch (MessagingException e) {
            logger.error("关闭流异常",e);
            return  false;
        } finally {
            if (transport != null) {
                transport = null;
            }
        }
        return  true;
    }


}
