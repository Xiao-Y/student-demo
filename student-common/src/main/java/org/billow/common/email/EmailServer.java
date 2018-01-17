package org.billow.common.email;

import org.apache.log4j.Logger;
import org.billow.model.custom.MailModel;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送服务
 *
 * @author liuyongtao
 * @create 2017-10-26 15:19
 */
@Service
public class EmailServer {
    private static final Logger logger = Logger.getLogger(EmailServer.class);

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;

    /**
     * 简单邮件发送
     *
     * @param toEmails 收件人邮箱，多个邮箱以“;”分隔
     * @param subject  邮件主题
     * @param content  邮件内容
     */
    public void singleMailSend(final String toEmails, final String subject, final String content) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("------------开始发送邮件----------");
                    // 建立邮件消息
                    MimeMessage message = javaMailSender.createMimeMessage();
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                    // 设置发件人邮箱
                    messageHelper.setFrom(simpleMailMessage.getFrom());
                    // 设置收件人邮箱
                    if (ToolsUtils.isNotEmpty(toEmails)) {
                        String[] toEmailArray = toEmails.split(";");
                        List<String> toEmailList = new ArrayList<>();
                        if (ToolsUtils.isEmpty(toEmailArray)) {
                            throw new RuntimeException("收件人邮箱不得为空！");
                        } else {
                            for (String s : toEmailArray) {
                                //过滤收件人为空的
                                if (ToolsUtils.isNotEmpty(s)) {
                                    toEmailList.add(s);
                                }
                            }
                            if (ToolsUtils.isEmpty(toEmailList)) {
                                throw new RuntimeException("收件人邮箱不得为空！");
                            } else {
                                toEmailArray = toEmailList.toArray(new String[toEmailList.size()]);
                            }
                        }
                        messageHelper.setTo(toEmailArray);
                    } else {
                        //默认的
                        messageHelper.setTo(simpleMailMessage.getTo());
                    }
                    // 邮件主题
                    if (ToolsUtils.isNotEmpty(subject)) {
                        messageHelper.setSubject(subject);
                    } else {
                        //默认的
                        messageHelper.setSubject(simpleMailMessage.getSubject());
                    }
                    // true 表示启动HTML格式的邮件
                    if (ToolsUtils.isNotEmpty(content)) {
                        messageHelper.setText(content, true);
                    } else {
                        messageHelper.setText(simpleMailMessage.getText(), true);
                    }
                    messageHelper.setSentDate(new Date());
                    // 发送邮件
                    javaMailSender.send(message);
                    logger.info("邮件主题-->" + subject);
                    logger.info("邮件内容-->" + content);
                    logger.info("------------发送邮件完成----------");
                } catch (MessagingException e) {
                    e.printStackTrace();
                    logger.info("------------发送邮件异常----------");
                }
            }
        }).start();
    }

    /**
     * 发送复杂邮件(图片/附件)
     *
     * @param mail
     */
    public void sendEmail(final MailModel mail) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("------------开始发送邮件----------");
                    // 建立邮件消息
                    MimeMessage message = javaMailSender.createMimeMessage();
                    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
                    // 设置发件人邮箱
                    if (mail.getEmailFrom() != null) {
                        messageHelper.setFrom(mail.getEmailFrom());
                    } else {
                        messageHelper.setFrom(simpleMailMessage.getFrom());
                    }
                    // 设置收件人邮箱
                    if (ToolsUtils.isNotEmpty(mail.getToEmails())) {
                        String[] toEmailArray = mail.getToEmails().split(";");
                        List<String> toEmailList = new ArrayList<>();
                        if (ToolsUtils.isEmpty(toEmailArray)) {
                            throw new RuntimeException("收件人邮箱不得为空！");
                        } else {
                            for (String s : toEmailArray) {
                                //过滤收件人为空的
                                if (ToolsUtils.isNotEmpty(s)) {
                                    toEmailList.add(s);
                                }
                            }
                            if (ToolsUtils.isEmpty(toEmailList)) {
                                throw new RuntimeException("收件人邮箱不得为空！");
                            } else {
                                toEmailArray = toEmailList.toArray(new String[toEmailList.size()]);
                            }
                        }
                        messageHelper.setTo(toEmailArray);
                    } else {
                        //默认的
                        messageHelper.setTo(simpleMailMessage.getTo());
                    }

                    // 邮件主题
                    if (ToolsUtils.isNotEmpty(mail.getSubject())) {
                        messageHelper.setSubject(mail.getSubject());
                    } else {
                        messageHelper.setSubject(simpleMailMessage.getSubject());
                    }

                    // true 表示启动HTML格式的邮件
                    if (ToolsUtils.isNotEmpty(mail.getContent())) {
                        messageHelper.setText(mail.getContent(), true);
                    } else {
                        messageHelper.setText(simpleMailMessage.getText(), true);
                    }

                    // 添加图片
                    if (null != mail.getPictures()) {
                        for (Map.Entry<String, String> entry : mail.getPictures().entrySet()) {
                            String cid = entry.getKey();
                            String filePath = entry.getValue();
                            if (null == cid || null == filePath) {
                                throw new RuntimeException("请确认每张图片的ID和图片地址是否齐全！");
                            }
                            File file = new File(filePath);
                            if (!file.exists()) {
                                throw new RuntimeException("图片" + filePath + "不存在！");
                            }
                            FileSystemResource img = new FileSystemResource(file);
                            messageHelper.addInline(cid, img);
                        }
                    }

                    // 添加附件
                    if (null != mail.getAttachments()) {
                        for (Map.Entry<String, String> entry : mail.getAttachments().entrySet()) {
                            String cid = entry.getKey();
                            String filePath = entry.getValue();
                            if (ToolsUtils.isEmpty(filePath) || ToolsUtils.isEmpty(cid)) {
                                throw new RuntimeException("请确认每个附件的ID和地址是否齐全！");
                            }
                            File file = new File(filePath);
                            if (!file.exists()) {
                                throw new RuntimeException("附件" + filePath + "不存在！");
                            }
                            FileSystemResource fileResource = new FileSystemResource(file);
                            messageHelper.addAttachment(cid, fileResource);
                        }
                    }
                    messageHelper.setSentDate(new Date());
                    // 发送邮件
                    javaMailSender.send(message);
                    logger.info("------------发送邮件完成----------");
                } catch (MessagingException e) {
                    e.printStackTrace();
                    logger.info("------------发送邮件异常----------");
                }
            }
        }).start();
    }
}
