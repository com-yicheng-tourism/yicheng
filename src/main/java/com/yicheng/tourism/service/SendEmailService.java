package com.yicheng.tourism.service;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.security.GeneralSecurityException;

public interface SendEmailService {
    boolean SendEamil(String msg,String receiver) throws GeneralSecurityException, MessagingException;
}
