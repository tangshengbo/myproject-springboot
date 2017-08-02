package com.tangshengbo.service;

/**
 * Created by Tangshengbo on 2017/8/2.
 */
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);
}
