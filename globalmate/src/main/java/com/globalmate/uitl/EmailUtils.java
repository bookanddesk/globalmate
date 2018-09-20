package com.globalmate.uitl;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @author XingJiajun
 * @Date 2018/9/20 15:04
 * @Description
 */
public class EmailUtils {


    public static String sendVerificationCode(String targetEmailAddress) {
        try {
            String verificationCode = RandomStringUtils.randomNumeric(GMConstant.EMAIL_VERIFICATION_CODE_COUNT);
            Email email = buildEmail();
            email.setFrom(GMConstant.EMAIL_FROM);
            email.setSubject(GMConstant.EMAIL_SUBJECT);
            email.setMsg(GMConstant.EMAIL_MSG_PREFIX + verificationCode);
            email.addTo(targetEmailAddress);
            email.send();
            return verificationCode;
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Email buildEmail() {
        Email email = new SimpleEmail();
        email.setHostName(GMConstant.EMAIL_HOST_NAME);
        email.setSmtpPort(GMConstant.EMAIL_HOST_PORT);
        email.setAuthenticator(new DefaultAuthenticator(GMConstant.EMAIL_AUTHENTICATION_USERNAME,
                GMConstant.EMAIL_AUTHENTICATION_PASSWORD));
        email.setSSLOnConnect(true);
        email.setCharset(GMConstant.EMAIL_CHARSET);
        return email;
    }

}
