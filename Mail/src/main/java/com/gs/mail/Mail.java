package com.gs.mail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
public class Mail {
    private String from;
    private String subject;
    private String context;
    private String contextType;
    private String bodyContext;
    private String to;
    private String cc;
    private String bcc;
    private List<String> attachments;

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public String getBodyContext() {
        return bodyContext;
    }

    public void setBodyContext(String bodyContext) {
        this.bodyContext = bodyContext;
    }

    public InternetAddress getFrom() {
        try {
            return InternetAddress.parse(from)[0];
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public InternetAddress[] getTo() {
        try {
            return InternetAddress.parse(to);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public InternetAddress[] getCc() {
        try {
            return InternetAddress.parse(cc);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public InternetAddress[] getBcc() {
        try {
            return InternetAddress.parse(bcc);
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
}
