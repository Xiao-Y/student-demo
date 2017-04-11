package org.billow.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;

public class MailConfig implements Cloneable {
	private static final Logger logger = Logger.getLogger(MailConfig.class);
	/**
	 * 无限制重发次数标志
	 */
	public static final int UNLIMITED_TIMES = -1;
	/**
	 * 邮件类型 文本
	 */
	public static final int MAIL_TYPE_TEXT = 1;
	/**
	 * 邮件类型 网页
	 */
	public static final int MAIL_TYPE_HTML = 2;
	/**
	 * 发信人
	 */
	private String from;
	/**
	 * 收信人
	 */
	private String[] tos;// 收件人
	/**
	 * 抄送人
	 */
	private String[] ccs;
	/**
	 * 主题
	 */
	private String subject;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 附件地址
	 */
	private String[] attachments;

	/**
	 * 附件文件名
	 */
	private String[] attachmentNames;
	/**
	 * 发送失败是，是否重新发送 true 重新发送 false 不重新发送.默认为false
	 */
	private boolean isRepeat;
	/**
	 * 重发的时间间隔 ，单位 秒
	 */
	private long period;// 重复间隔，单位秒
	/**
	 * 重发次数，如果 为 0 ，表示无限制，直到发送成功为止
	 */
	private int frequency;
	/**
	 * 邮件类型
	 */
	private int mailType;

	/**
	 * 获取 发件人
	 * 
	 * @return
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * 设置 发件人
	 * 
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 获取 主题
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置 主题
	 * 
	 * @param from
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获取 内容
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置 内容
	 * 
	 * @param from
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取 收件人
	 * 
	 * @return
	 */
	public String[] getTos() {
		return tos;
	}

	/**
	 * 设置 收件人
	 * 
	 * @param from
	 */
	public void setTos(String[] tos) {
		this.tos = tos;
	}

	/**
	 * 获取 抄送人
	 * 
	 * @return
	 */
	public String[] getCcs() {
		return ccs;
	}

	/**
	 * 设置 抄送人
	 * 
	 * @param from
	 */
	public void setCcs(String[] ccs) {
		this.ccs = ccs;
	}

	/**
	 * 获取 邮件附件
	 * 
	 * @return
	 */
	public String[] getAttachments() {
		return attachments;
	}

	/**
	 * 设置 附件
	 * 
	 * @param from
	 */
	public void setAttachments(String[] attachments) {
		this.attachments = attachments;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 获取 获取重发次数
	 * 
	 * @return
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * 设置 重发次数(在发送失败并且isRepeat 为true是生效)
	 * 
	 * @param from
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * 获取 是否重发 默认为false 不重发
	 * 
	 * @return
	 */
	public boolean isRepeat() {
		return isRepeat;
	}

	/**
	 * 设置 设置在发送失败时是否重发
	 * 
	 * @param from
	 */
	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	/**
	 * 获取 重发时间间隔 单位：秒
	 * 
	 * @return
	 */
	public long getPeriod() {
		return period;
	}

	/**
	 * 设置 重发的时间间隔(在发送失败并且isRepeat为true时生效) 单位：秒
	 * 
	 * @param from
	 */
	public void setPeriod(long period) {
		this.period = period;
	}

	/**
	 * 获取附件文件名
	 * 
	 * @return
	 */
	public String[] getAttachmentNames() {
		return attachmentNames;
	}

	/**
	 * 设置附件文件名
	 * 
	 * @return
	 */
	public void setAttachmentNames(String[] attachmentNames) {
		this.attachmentNames = attachmentNames;
	}

	/**
	 * 获取邮件类型
	 * 
	 * @return
	 */
	public int getMailType() {
		return mailType;
	}

	/**
	 * 设置邮件类型
	 * 
	 * @param mailType
	 */
	public void setMailType(int mailType) {
		this.mailType = mailType;
	}

	/**
	 * 复制 当前对象，复制的对象和当前对象是完全独立的
	 * 
	 * @return
	 */
	public MailConfig copy() {
		MailConfig copy = null;
		try {
			copy = (MailConfig) this.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		// copy.setFrom(from);
		// copy.setSubject(subject);
		// copy.setContent(content);
		if (tos != null) {
			String[] toscopy = new String[tos.length];
			for (int i = 0; i < tos.length; i++) {
				toscopy[i] = tos[i];
			}
			copy.setTos(toscopy);
		}
		if (ccs != null) {
			String[] ccscopy = new String[ccs.length];
			for (int i = 0; i < ccs.length; i++) {
				ccscopy[i] = ccs[i];
			}
			copy.setCcs(ccscopy);
		}
		if (attachments != null) {
			String[] attachmentscopy = new String[attachments.length];
			for (int i = 0; i < attachments.length; i++) {
				attachmentscopy[i] = attachments[i];
			}
			copy.setAttachments(attachmentscopy);
		}
		if (attachmentNames != null) {
			String[] attachmentNamescopy = new String[attachmentNames.length];
			for (int i = 0; i < attachmentNames.length; i++) {
				attachmentNamescopy[i] = attachmentNames[i];
			}
			copy.setAttachmentNames(attachmentNamescopy);
		}
		return copy;
	}
}
