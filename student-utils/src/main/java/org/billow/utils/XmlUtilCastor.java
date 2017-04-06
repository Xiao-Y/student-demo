package org.billow.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLContext;

/**
 * 实现将xml解析成dto和将dto生成xml的功能 本类是基于digester编写的
 */
public class XmlUtilCastor {
	private static final Logger logger = Logger.getLogger(XmlUtilCastor.class);

	/**
	 * xml 转成 对象.
	 * 
	 * @param xmlPath
	 *            xml文件路径,绝对路径
	 * @param rulePath
	 *            映射规则文件路径,绝对路径
	 * @return
	 */
	public static Object xml2Object(String xmlPath, String rulePath, String charset) {
		Mapping mapping = new Mapping();
		try {
			mapping.loadMapping(rulePath);
		} catch (Exception e) {
			throw new RuntimeException("load " + rulePath + " failed!", e);
		}
		InputStreamReader reader;
		try {
			if (charset != null) {
				reader = new InputStreamReader(new FileInputStream(new File(xmlPath)), charset);
			} else {
				reader = new InputStreamReader(new FileInputStream(new File(xmlPath)));
			}

		} catch (Exception e) {
			throw new RuntimeException("load " + xmlPath + " failed!", e);
		}
		Object o;
		try {
			Unmarshaller unmarshaller = new Unmarshaller(mapping);
			o = unmarshaller.unmarshal(reader);
		} catch (Exception e) {
			throw new RuntimeException("Parse xml to object failed!", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return o;
	}

	/**
	 * xml 转成 对象.
	 * 
	 * @param xmlContent
	 *            xml内容
	 * @param rulePath
	 *            映射规则文件路径
	 * @return
	 */
	public static Object xml2Object(StringBuilder xmlContent, String rulePath, String charset) {
		Mapping mapping = new Mapping();
		try {
			mapping.loadMapping(rulePath);
		} catch (Exception e) {
			throw new RuntimeException("load " + rulePath + " failed!", e);
		}
		InputStreamReader reader;
		try {
			if (charset != null) {
				reader = new InputStreamReader(new ByteArrayInputStream(xmlContent.toString().getBytes(charset)), charset);
			} else {
				reader = new InputStreamReader(new ByteArrayInputStream(xmlContent.toString().getBytes()));
			}

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unsupported Encoding", e);
		}
		Object o;
		try {
			Unmarshaller unmarshaller = new Unmarshaller(mapping);
			o = unmarshaller.unmarshal(reader);
		} catch (Exception e) {
			throw new RuntimeException("Parse xml to object failed!", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return o;
	}

	/**
	 * 对象转xml
	 * 
	 * @param rulePath
	 *            映射规则文件路径
	 * @param outputPath
	 *            输入xml文件的路径
	 * @param obj
	 *            对象
	 * @param newRootName
	 *            生成的xml文件根节点的新名字
	 */
	@SuppressWarnings("resource")
	public static void object2Xml(String rulePath, String outputPath, Object obj, String newRootName, String charset) {
		XMLContext context = new XMLContext();
		Mapping mapping = context.createMapping();
		Writer writer;
		try {
			if (charset != null) {
				writer = new OutputStreamWriter(new FileOutputStream(outputPath), charset);
			} else {
				writer = new OutputStreamWriter(new FileOutputStream(outputPath));
			}

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unsupported Encoding!", e);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File not found for" + outputPath, e);
		}
		try {
			mapping.loadMapping(rulePath);
			context.addMapping(mapping);
		} catch (Exception e) {
			throw new RuntimeException("load " + rulePath + " failed!", e);
		}
		Marshaller marshaller = context.createMarshaller();
		marshaller.setSuppressXSIType(true); // 去掉xsi:type
		if (newRootName != null && !newRootName.equals("")) {
			marshaller.setRootElement(newRootName);// 给顶层元素改个名字
		}
		try {
			marshaller.setWriter(writer);
			marshaller.marshal(obj);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException("Object to xml failed!", e);
		}
	}

	/**
	 * 对象转xml
	 * 
	 * @param rulePath
	 *            映射规则文件路径
	 * @param obj
	 *            对象
	 * @param newRootName
	 *            生成的xml文件根节点的新名字
	 * @return
	 */
	public static StringBuilder object2Xml(String rulePath, Object obj, String newRootName) {
		XMLContext context = new XMLContext();
		Mapping mapping = context.createMapping();
		Writer writer = new StringWriter();
		try {
			mapping.loadMapping(rulePath);
			context.addMapping(mapping);
		} catch (Exception e) {
			throw new RuntimeException("load " + rulePath + " failed!", e);
		}
		Marshaller marshaller = context.createMarshaller();
		marshaller.setSuppressXSIType(true); // 去掉xsi:type
		if (newRootName != null && !newRootName.equals("")) {
			marshaller.setRootElement(newRootName);// 给顶层元素改个名字
		}
		try {
			marshaller.setWriter(writer);
			marshaller.marshal(obj);
		} catch (Exception e) {
			throw new RuntimeException("Object to xml failed!", e);
		}
		String xmlContent = writer.toString();
		return new StringBuilder().append(xmlContent);
	}

	public static StringBuilder readXml(String tempFile, String charset) {
		File ff = null;
		String line;
		BufferedReader inbr = null;
		StringBuilder tempbf = new StringBuilder();
		try {
			ff = new File(tempFile);
			inbr = new BufferedReader(new InputStreamReader(new FileInputStream(ff), Charset.forName(charset)));
			int i = 0;
			while ((line = inbr.readLine()) != null) {
				if (i == 0) {
					if (line.indexOf("<") != 0)
						line = line.substring(1);// 去掉文件头
					i++;
				}
				tempbf.append(line);
			}
			inbr.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (inbr != null)
				try {
					inbr.close();
				} catch (IOException e) {
				}
			inbr = null;
			ff = null;
		}
		return tempbf;
	}
}
