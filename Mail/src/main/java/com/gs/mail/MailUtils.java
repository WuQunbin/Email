package com.gs.mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by Administrator on 2017/9/22.
 */
public class MailUtils {
    public static void sendMail(Mail mail){
        Properties properties = ConfigUtils.build("src/main/resources/mail.properties");
        Session session = Session.getInstance(properties,
                new MailAuthenticator(ConfigUtils.getString("username"),
                        ConfigUtils.getString("password")));
        mail.setFrom(ConfigUtils.getString("username"));
        try {
            Message message = new MimeMessage(session);
            message.setSubject(mail.getSubject());
            if(mail.getContext()!=null){
                message.setContent(mail.getContext(),mail.getContextType());
            }else {
                //组装Multipart
                Multipart multipart = new MimeMultipart();
                BodyPart contentBody = new MimeBodyPart();
                contentBody.setContent(mail.getBodyContext(),mail.getContextType());
                multipart.addBodyPart(contentBody);
                for (String filePath:mail.getAttachments()){
                    BodyPart fileBody = new MimeBodyPart();
                    fileBody.setDataHandler(new DataHandler(new FileDataSource(filePath)));
                    multipart.addBodyPart(fileBody);
                }
                message.setContent(multipart);
            }

            message.setFrom(mail.getFrom());

            message.setRecipients(Message.RecipientType.TO,mail.getTo());
            message.setRecipients(Message.RecipientType.CC,mail.getCc());
            message.setRecipients(Message.RecipientType.BCC,mail.getBcc());

            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(message,message.getAllRecipients());
        }catch (NoSuchProviderException e){
            e.printStackTrace();
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Thread(new Runnable() {
            public void run() {
                Mail mail = new Mail();
                mail.setSubject("国庆放假！");
//                mail.setContext("中秋国庆假期已经出来了，准备好了去哪里玩了吗？<a href='http://baidu.com'>百度一下</a>" +
//                        "<img src='https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3455205360,3244468299&fm=27&gp=0.jpg'/>");
                mail.setBodyContext("中秋国庆假期已经出来了，准备好了去哪里玩了吗？");
                mail.setContextType("text/html;charset=utf8");
                List<String> attachments = new ArrayList<String>();
                //可以添加多个附件
                attachments.add("src/main/resources/mail.properties");
                mail.setAttachments(attachments);
                mail.setTo("htjackwu@foxmail.com");
                mail.setCc("htjackwu@foxmail.com");
                mail.setBcc("htjackwu@foxmail.com");
                MailUtils.sendMail(mail);
            }
        }).start();
    }
}
