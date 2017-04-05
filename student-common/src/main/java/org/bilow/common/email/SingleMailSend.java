package org.bilow.common.email;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class SingleMailSend {

	public static void main(String[] args) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("mail.sinosoft.com.cn");
		senderImpl.setUsername("liuytsz@sinosoft.com.cn"); // 根据自己的情况,设置username
		senderImpl.setPassword("————————"); // 根据自己的情况, 设置password
		senderImpl.setPort(25);

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.timeout", 25000);
		senderImpl.setJavaMailProperties(properties);

		// 建立邮件消息
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 设置收件人，寄件人 用数组发送多个邮件
		// String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
		// mailMessage.setTo(array);
		mailMessage.setTo("lyongtao123@126.com");
		mailMessage.setFrom("liuytsz@sinosoft.com.cn");
		mailMessage.setSubject("测试简单文本邮件发送！");
		mailMessage.setText("测试我的简单邮件发送机制！！ ");
		// 发送邮件
		senderImpl.send(mailMessage);

		System.out.println("邮件发送成功.. ");
	}
}
