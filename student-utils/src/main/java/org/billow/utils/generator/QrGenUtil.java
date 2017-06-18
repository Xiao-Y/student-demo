package org.billow.utils.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

/**
 * 二维码生成
 * 
 * @author XiaoY
 * @date: 2017年6月18日 上午11:30:15
 */
public class QrGenUtil {

	/**
	 * 生成二维码图片
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @author XiaoY
	 * @date: 2017年6月18日 上午11:30:37
	 */
	public static ByteArrayOutputStream createQrGen(String url) throws IOException {
		// 如果有中文，可使用withCharset("UTF-8")方法
		// 设置二维码url链接，图片宽度250*250，JPG类型
		return QRCode.from(url).withSize(250, 250).to(ImageType.JPG).stream();
	}
}
