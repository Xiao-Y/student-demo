package org.billow.build.readxml;

import org.billow.build.Utils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取配置文件
 * 
 * @author Liuyongtao
 * @version $Id: ReadConfog.java 2016年1月13日 上午11:22:47 $
 */
public class ReadConfog {

	public Map<String, Object> readXml() {
		Map<String, Object> map = new HashMap<String, Object>();
		String configPath = Utils.getConfigPath();
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(configPath));
			Element root = document.getRootElement();

			map.put("authorName", root.elementTextTrim("authorName"));
			map.put("authorMail", root.elementTextTrim("authorMail"));
			map.put("version", root.elementTextTrim("version"));

			map.put("tableName", root.elementTextTrim("tableName"));

			map.put("controllerPackageName", root.elementTextTrim("controllerPackageName"));
			map.put("controllerClazzName", root.elementTextTrim("controllerClazzName"));
			map.put("explainController", root.elementTextTrim("explainController"));

			map.put("servicePackageName", root.elementTextTrim("servicePackageName"));
			map.put("serviceClazzName", root.elementTextTrim("serviceClazzName"));
			map.put("explainService", root.elementTextTrim("explainService"));

			map.put("serviceImplPackageName", root.elementTextTrim("serviceImplPackageName"));
			map.put("serviceImplClazzName", root.elementTextTrim("serviceImplClazzName"));
			map.put("explainServiceImpl", root.elementTextTrim("explainServiceImpl"));

			map.put("daoPackageName", root.elementTextTrim("daoPackageName"));
			map.put("daoClazzName", root.elementTextTrim("daoClazzName"));
			map.put("explainDao", root.elementTextTrim("explainDao"));

			// map.put("daoImplPackageName",
			// root.elementTextTrim("daoImplPackageName"));
			// map.put("daoImplClazzName",
			// root.elementTextTrim("daoImplClazzName"));
			// map.put("explainDaoImpl",
			// root.elementTextTrim("explainDaoImpl"));

			map.put("modelBasePackageName", root.elementTextTrim("modelBasePackageName"));
			map.put("modelBaseClazzName", root.elementTextTrim("modelBaseClazzName"));
			map.put("explainModelBase", root.elementTextTrim("explainModelBase"));

			map.put("modelPackageName", root.elementTextTrim("modelPackageName"));
			map.put("modelClazzName", root.elementTextTrim("modelClazzName"));
			map.put("explainModel", root.elementTextTrim("explainModel"));

			map.put("mapperPackageName", root.elementTextTrim("mapperPackageName"));
			map.put("mapperXMLName", root.elementTextTrim("mapperXMLName"));
			
			map.put("mapperBasePackageName", root.elementTextTrim("mapperBasePackageName"));
			map.put("mapperBaseXMLName", root.elementTextTrim("mapperBaseXMLName"));
			// Element fields = root.element("fields");
			// Iterator<Element> iterator = fields.elementIterator("field");
			// List<FieldModel> list = new ArrayList<FieldModel>();
			// while (iterator.hasNext()) {
			// FieldModel fm = new FieldModel();
			// Element field = iterator.next();
			// fm.setDbFieldName(field.elementTextTrim("dbFieldName"));
			// fm.setFieldName(field.elementTextTrim("fieldName"));
			// fm.setFieldType(field.elementTextTrim("fieldType"));
			// fm.setFieldLength(field.elementTextTrim("fieldLength"));
			// fm.setNullable(field.elementTextTrim("nullable"));
			// list.add(fm);
			// }
			// map.put("fields", list);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	// public static void main(String[] args) {
	// ReadConfog rc = new ReadConfog();
	// Map<String, Object> xml = rc.readXml();
	// System.out.println(xml);
	// }

}
