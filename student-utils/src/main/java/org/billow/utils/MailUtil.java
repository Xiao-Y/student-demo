package org.billow.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil implements Runnable, Cloneable {
	private static final Logger logger = Logger.getLogger(MailUtil.class);
	private JavaMailSenderImpl mailSender;
	private Timer timer = new Timer(true);
	private MailConfig config;
	private int sendTimes = 0;

	public void send(MailConfig config) {
		MailUtil copy = new MailUtil();
		copy.mailSender = mailSender;
		copy.timer = timer;
		copy.config = config.copy();
		Thread t = new Thread(copy);
		t.start();
	}

	public void run() {
		try {
			doSend();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (config.isRepeat()) {
				long period = config.getPeriod();
				timer.schedule(new TimerTask() {
					public void run() {
						int frequency = config.getFrequency(); // 最大重发次数
						if (frequency != MailConfig.UNLIMITED_TIMES && sendTimes >= frequency) {
							cancel();
							return;
						}
						sendTimes++;
						try {
							doSend();
						} catch (Exception e) {
							return;
						}
						cancel();

					}
				}, 0, period * 1000);

			}
		}
	}

	private void doSend() {
		// TODO Auto-generated method stub
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = mailSender.createMimeMessage();
		// 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(mailMessage, true, "GBK");
			// 设置收件人，寄件人
			if (config.getTos() != null && config.getTos().length > 0) {
				messageHelper.setTo(config.getTos());
			}
			if (config.getCcs() != null && config.getCcs().length > 0) {
				messageHelper.setCc(config.getCcs());
			}
			if (config.getFrom() != null) {
				messageHelper.setFrom(config.getFrom());
			}
			messageHelper.setSubject(config.getSubject());
			// true 表示启动HTML格式的邮件
			StringBuilder textBuffer = new StringBuilder();
			if (config.getMailType() == MailConfig.MAIL_TYPE_HTML) {
				textBuffer
						.append("<html><head><mate http-equiv=Content-Type content=\"text/html;charset=GBK\"><style>body{ font-size:12px}</style></head><body>");
				textBuffer.append(config.getContent());
				textBuffer.append("</body></html>");
			} else {
				textBuffer.append(config.getContent());
			}
			messageHelper.setText(textBuffer.toString(), true);

			String[] attachments = config.getAttachments();
			String[] attachmentNames = config.getAttachmentNames();
			FileSystemResource fsr = null;
			if (attachments != null && attachments.length > 0) {
				for (int i = 0; i < attachments.length; i++) {
					File file = new File(attachments[i]);
					String fileName = null;
					boolean fromAttName = false;
					try {
						if (attachmentNames != null && attachmentNames.length == attachments.length) {
							if (attachmentNames[i] != null && !attachmentNames[i].equals("")) {
								fromAttName = true;
								fileName = MimeUtility.encodeWord(attachmentNames[i]);
							}
						}
						if (fileName == null) {
							fileName = MimeUtility.encodeWord(file.getName());
						}
					} catch (UnsupportedEncodingException e) {
						if (fromAttName) {
							fileName = attachmentNames[i];
						} else {
							fileName = file.getName();
						}
					}
					fsr = new FileSystemResource(file);
					messageHelper.addAttachment(fileName, fsr);
				}
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		// 发送邮件
		mailSender.send(mailMessage);
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
}
