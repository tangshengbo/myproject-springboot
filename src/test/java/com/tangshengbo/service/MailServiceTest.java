package com.tangshengbo.service;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Tangshengbo on 2017/8/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService MailService;

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void testSimpleMail() throws Exception {
        MailService.sendSimpleMail("867850520@qq.com", "test simple mail", " hello this is simple mail org.jasypt.encryption");
    }

    @Test
    public void testStringEncode() {
        String userName = stringEncryptor.encrypt("XXX");
        String password = stringEncryptor.encrypt("XXX");
        System.out.println(userName + "\t" + password);
    }
}
