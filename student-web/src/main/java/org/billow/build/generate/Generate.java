package org.billow.build.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.billow.build.data.SetData;
import org.billow.build.model.MapperDaoModel;
import org.billow.build.model.ModelModel;
import org.billow.build.model.OtherModel;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Generate {

	// 项目绝对路径
	private static String BASEPATH = System.getProperty("user.dir");
	// 模板文件的存放路径，这里是存放在项目根目录下的ftl文件夹中（相对路径）
	public static final String FTLS_PATH = "ftl";
	// 模板文件所在的文件夹(绝对路径)
	public static String ABSOLUTE_FTLS_PATH = BASEPATH + "/" + FTLS_PATH;

	private static Configuration cfg;
	private static Template template;
	private static Writer writer;

	/**
	 * 开始生成文件
	 */
	public static void generateFile() {
		System.out.println("java文件开始生成....");
		SetData sd = SetData.getInstance();
		try {
			// 创建Freemarker配置实例
			cfg = new Configuration();
			// 工程目录下的目录（ftl在工程下）--推荐
			cfg.setDirectoryForTemplateLoading(new File(FTLS_PATH));
			// 添加一个"宏"共享变量用来将属性名首字母大写
			cfg.setSharedVariable("upperFC", new UpperFirstCharacter());
			File absoluteFtls = new File(ABSOLUTE_FTLS_PATH);
			String[] ftlNames = absoluteFtls.list();// 获取ftl文件夹下的文件名称
			for (String ftlName : ftlNames) {
				Generate.writerFile(ftlName, sd);
			}
			System.out.println("java文件自动生成完成....");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过模板写文件
	 * 
	 * @param ftlName
	 *            模板名称
	 * @param sd
	 *            模板数据源
	 * @throws IOException
	 * @throws TemplateException
	 */
	private static void writerFile(String ftlName, SetData sd) throws IOException, TemplateException {
		System.out.println("使用 " + ftlName + "模板生成java文件...");
		// 加载模板文件
		template = cfg.getTemplate(ftlName);

		ModelModel mm = null;
		OtherModel om = null;
		MapperDaoModel mdm = null;
		if ("modelBase.ftl".equals(ftlName)) {
			mm = sd.getModelBase();
		} else if ("modelDto.ftl".equals(ftlName)) {
			mm = sd.getModel();
		} else if ("controller.ftl".equals(ftlName)) {
			om = sd.getController();
		} else if ("service.ftl".equals(ftlName)) {
			om = sd.getService();
		} else if ("serviceImpl.ftl".equals(ftlName)) {
			om = sd.getServiceImpl();
		} else if ("dao.ftl".equals(ftlName)) {
			om = sd.getDao();
		} else if ("mapperDao.ftl".equals(ftlName)) {
			mdm = sd.getMapperDao();
		}

		String fileOutPath = "";
		String clazzName = "";
		if (mm != null) {
			fileOutPath = BASEPATH + "/src/main/java/" + mm.getPackageName().replace(".", "/");
			clazzName = mm.getClazzName() + ".java";
		} else if (om != null) {
			fileOutPath = BASEPATH + "/src/main/java/" + om.getPackageName().replace(".", "/");
			clazzName = om.getClazzName() + ".java";
		} else {
			fileOutPath = BASEPATH + "/src/main/resources/" + mdm.getPackageName().replace(".", "/");
			clazzName = mdm.getClazzName() + ".xml";
		}
		// java文件夹输出路径
		File fileOutPathFile = new File(fileOutPath);
		if (!fileOutPathFile.exists()) {
			fileOutPathFile.mkdirs();
		}
		System.out.println("文件路径：" + fileOutPath);
		// java文件输出路径
		fileOutPath += "/" + clazzName;
		fileOutPathFile = new File(fileOutPath);
		if (!fileOutPathFile.exists()) {
			fileOutPathFile.createNewFile();
		}
		System.out.println("文件名：" + clazzName);
		// 显示生成的数据
		writer = new FileWriter(fileOutPathFile);
		if (mm != null) {
			template.process(mm, writer);
		} else if (om != null) {
			template.process(om, writer);
		} else if (mdm != null) {
			template.process(mdm, writer);
		}
		writer.flush();
		writer.close();
	}
}
