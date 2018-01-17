package org.billow.model.custom;

import java.util.Map;

/**
 * 邮件实体类
 *
 * @author liuyongtao
 * @create 2017-10-26 15:11
 */
public class MailModel {

    public MailModel() {
    }

    /**
     * 普通构造器
     *
     * @param toEmails 收件人邮箱，多个邮箱以“;”分隔
     * @param subject  邮件主题
     * @param content  邮件内容
     */
    public MailModel(String toEmails, String subject, String content) {
        this.toEmails = toEmails;
        this.subject = subject;
        this.content = content;
    }

    //收件人邮箱，多个邮箱以“;”分隔
    private String toEmails;
    //邮件主题
    private String subject;
    //邮件内容
    private String content;
    //附件
    private String[] attachFileNames;
    //邮件中的图片，为空时无图片。map中的key为图片ID，value为图片地址
    private Map<String, String> pictures;
    //邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址
    private Map<String, String> attachments;

    //发送人地址1个
    private String fromAddress;
    //发件人邮箱服务器
    private String emailHost;
    //发件人邮箱
    private String emailFrom;
    //发件人用户名
    private String emailUserName;
    //发件人密码
    private String emailPassword;

    /**
     * 发送人地址1个
     *
     * @return
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * 发送人地址1个
     *
     * @param fromAddress
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * 邮件主题
     *
     * @return
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 邮件主题
     *
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 邮件内容
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 邮件内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 附件
     *
     * @return
     */
    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    /**
     * 附件
     *
     * @param attachFileNames
     */
    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }

    /**
     * 发件人邮箱服务器
     *
     * @return
     */
    public String getEmailHost() {
        return emailHost;
    }

    /**
     * 发件人邮箱服务器
     *
     * @param emailHost
     */
    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    /**
     * 发件人邮箱
     *
     * @return
     */
    public String getEmailFrom() {
        return emailFrom;
    }

    /**
     * 发件人邮箱
     *
     * @param emailFrom
     */
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    /**
     * 发件人用户名
     *
     * @return
     */
    public String getEmailUserName() {
        return emailUserName;
    }

    /**
     * 发件人用户名
     *
     * @param emailUserName
     */
    public void setEmailUserName(String emailUserName) {
        this.emailUserName = emailUserName;
    }

    /**
     * 发件人密码
     *
     * @return
     */
    public String getEmailPassword() {
        return emailPassword;
    }

    /**
     * 发件人密码
     *
     * @param emailPassword
     */
    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    /**
     * 收件人邮箱，多个邮箱以“;”分隔
     *
     * @return
     */
    public String getToEmails() {
        return toEmails;
    }

    /**
     * 收件人邮箱，多个邮箱以“;”分隔
     *
     * @param toEmails
     */
    public void setToEmails(String toEmails) {
        this.toEmails = toEmails;
    }

    /**
     * 邮件中的图片，为空时无图片。map中的key为图片ID，value为图片地址
     *
     * @return
     */
    public Map<String, String> getPictures() {
        return pictures;
    }

    /**
     * 邮件中的图片，为空时无图片。map中的key为图片ID，value为图片地址
     *
     * @param pictures
     */
    public void setPictures(Map<String, String> pictures) {
        this.pictures = pictures;
    }

    /**
     * 邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址
     *
     * @return
     */
    public Map<String, String> getAttachments() {
        return attachments;
    }

    /**
     * 邮件中的附件，为空时无附件。map中的key为附件ID，value为附件地址
     *
     * @param attachments
     */
    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }
}

