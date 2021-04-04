package com.hk.server.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @program: gg2020
 * @description: 邮件发送工具类
 * @author: 头微凉
 * @create: 2020-09-15 16:46
 */
public class EmailUtil extends Thread {
    //用于给用户发送邮件的邮箱
    private String from = "1053619508@qq.com";
    //邮箱的用户名
    private String username = "1053619508@qq.com";
    //邮箱的密码
    private String password = "fxlyhoippwljbfhb";
    //发送邮件的服务器地址
    private String host = "smtp.qq.com";

    private String content;
    private String targetEmail;

    public EmailUtil(String content, String targetEmail) {
        this.content = content;
        this.targetEmail = targetEmail;
    }

    @Override
    public void run() {
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", host);
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");

            // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            //1、创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
                    return new PasswordAuthentication(from, password);
                }
            });
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
//            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect(host, username, password);
            //4、创建邮件
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); //发件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetEmail)); //收件人
            message.setSubject("审核结果"); //邮件的标题
            message.setContent(content, "text/html;charset=UTF-8");
            message.saveChanges();
            //发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new EmailUtil("快来帮我们修复服务器", "1402449728@qq.com").start();

    }
}