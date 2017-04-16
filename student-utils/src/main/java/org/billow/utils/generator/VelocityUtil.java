package org.billow.utils.generator;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * 模板填充数据帮助类
 * 
 * @author Sinosoft
 * 
 */
public class VelocityUtil {

	public static String mergeTemplateIntoString(String template, Map<String, Object> model) throws Exception {
		// 填充后返回值
		StringBuffer buff = null;
		// 初始化并取得Velocity引擎
		VelocityEngine engine = new VelocityEngine();
		// 取得velocity的上下文context
		VelocityContext context = new VelocityContext(model);
		StringWriter writer = new StringWriter();
		engine.evaluate(context, writer, "", template);
		buff = writer.getBuffer();
		try {
			writer.close();
		} catch (IOException e) {
		}
		return buff.toString();
	}

	public static void main(String[] args) throws Exception {
		String template = "${owner}：您的${type} : ${bill} 在  ${date} 日已支付成功";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("owner", "nassir");
		model.put("bill", "201203221000029763");
		model.put("type", "订单");
		model.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		System.out.println(mergeTemplateIntoString(template, model));

	}
}
