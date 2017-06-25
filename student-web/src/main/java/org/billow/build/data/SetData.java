package org.billow.build.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.billow.build.model.BaseModel;
import org.billow.build.model.ColumnModel;
import org.billow.build.model.FieldModel;
import org.billow.build.model.MapperDaoModel;
import org.billow.build.model.ModelModel;
import org.billow.build.model.OtherModel;
import org.billow.build.readxml.ReadConfog;
import org.billow.build.table.ReadDB;
import org.billow.utils.StringUtils;

/**
 * 为模板准备数据
 * 
 * @author Liuyongtao
 * @version $Id: SetData.java 2016年1月13日 上午10:48:11 $
 */
public class SetData {

	public static final String DATE_FROMATE = "yyyy-MM-dd HH:mm:ss";

	private ModelModel model;
	private ModelModel modelBase;
	private OtherModel controller;
	private OtherModel service;
	private OtherModel serviceImpl;
	private OtherModel dao;
	private MapperDaoModel mapperDao;
	// private OtherModel daoImpl;
	Map<String, Object> map;
	Map<String, ColumnModel> columns;

	// ----------单例模式：懒汉式，线程安全----start---------
	private static SetData SD;

	public static synchronized SetData getInstance() {
		if (SD == null) {
			SD = new SetData();
		}
		return SD;
	}

	private SetData() {
		ReadConfog rc = new ReadConfog();
		map = rc.readXml();
		modelBase = this.setModelBaseData();
		model = this.setModelData();
		controller = this.setControllerData();
		service = this.setServiceData();
		serviceImpl = this.setServiceImplData();
		dao = this.setDaoData();
		// daoImpl = this.setDaoImplData();
		mapperDao = this.setMapperDaoData();
	}

	// ----------单例模式：懒汉式，线程安全----end---------

	/**
	 * 添加mapperDao的数据
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月25日 下午8:28:08
	 */
	private MapperDaoModel setMapperDaoData() {
		MapperDaoModel mapperDaoModel = new MapperDaoModel();
		String namespace = map.get("daoPackageName") + "." + map.get("daoClazzName");
		mapperDaoModel.setNamespace(namespace);
		String type = map.get("modelPackageName") + "." + map.get("modelClazzName");
		mapperDaoModel.setType(type);
		mapperDaoModel.setColumns(columns);
		Set<String> keySet = columns.keySet();
		String columnStr = org.apache.commons.lang3.StringUtils.join(keySet, ",");
		mapperDaoModel.setColumnStr(columnStr);
		mapperDaoModel.setTableName((String) map.get("tableName"));
		mapperDaoModel.setPackageName((String) map.get("mapperPackageName"));
		mapperDaoModel.setClazzName((String) map.get("mapperXMLName"));
		return mapperDaoModel;
	}

	/**
	 * 添加modelBase的数据
	 * 
	 * @return model层摸model
	 */
	private ModelModel setModelBaseData() {
		ModelModel model = new ModelModel();
		// 添加基础数据
		this.setBaseData(model);
		model.setPackageName((String) map.get("modelBasePackageName"));
		model.setTableName((String) map.get("tableName"));
		model.setClazzName((String) map.get("modelBaseClazzName"));
		model.setExplain((String) map.get("explainModelBase"));
		// 添加字段数据
		this.setFieldData(model);
		return model;
	}

	/**
	 * 添加model的数据
	 * 
	 * @return model层摸model
	 */
	private ModelModel setModelData() {
		ModelModel model = new ModelModel();
		// 添加基础数据
		this.setBaseData(model);
		model.setPackageName((String) map.get("modelPackageName"));
		model.setTableName((String) map.get("tableName"));
		model.setClazzName((String) map.get("modelClazzName"));
		model.setExplain((String) map.get("explainModel"));
		model.setModelBasePackageName((String) map.get("modelBasePackageName"));
		model.setModelBaseClazzName((String) map.get("modelBaseClazzName"));
		return model;
	}

	/**
	 * 添加Controller层的数据
	 * 
	 * @return OtherModel
	 */
	private OtherModel setControllerData() {
		OtherModel controller = new OtherModel();
		// 添加基础数据
		this.setBaseData(controller);
		controller.setPackageName((String) map.get("controllerPackageName"));
		controller.setClazzName((String) map.get("controllerClazzName"));
		controller.setExplain((String) map.get("explainController"));
		return controller;
	}

	/**
	 * 添加Service层的数据
	 * 
	 * @return OtherModel
	 */
	private OtherModel setServiceData() {
		OtherModel service = new OtherModel();
		// 添加基础数据
		this.setBaseData(service);
		service.setPackageName((String) map.get("servicePackageName"));
		service.setClazzName((String) map.get("serviceClazzName"));
		service.setExplain((String) map.get("explainService"));
		this.setGeneric(service);
		return service;
	}

	/**
	 * 添加ServiceImpl层的数据
	 * 
	 * @return OtherModel
	 */
	private OtherModel setServiceImplData() {
		OtherModel service = new OtherModel();
		// 添加基础数据
		this.setBaseData(service);
		service.setPackageName((String) map.get("serviceImplPackageName"));
		service.setClazzName((String) map.get("serviceImplClazzName"));
		service.setExplain((String) map.get("explainServiceImpl"));
		service.setServiceInterfaceName((String) map.get("serviceClazzName"));
		service.setServiceInterfacelPackage((String) map.get("servicePackageName"));
		service.setDaoInterfacelPackage((String) map.get("daoPackageName"));
		service.setDaoInterfaceName((String) map.get("daoClazzName"));
		this.setGeneric(service);
		return service;
	}

	/**
	 * 添加Dao层的数据
	 * 
	 * @return OtherModel
	 */
	private OtherModel setDaoData() {
		OtherModel dao = new OtherModel();
		// 添加基础数据
		this.setBaseData(dao);
		dao.setPackageName((String) map.get("daoPackageName"));
		dao.setClazzName((String) map.get("daoClazzName"));
		dao.setExplain((String) map.get("explainDao"));
		this.setGeneric(dao);
		return dao;
	}

	// /**
	// * 添加DaoImpl层的数据
	// *
	// * @return OtherModel
	// */
	// private OtherModel setDaoImplData() {
	// OtherModel dao = new OtherModel();
	// // 添加基础数据
	// this.setBaseData(dao);
	// dao.setPackageName((String) map.get("daoImplPackageName"));
	// dao.setClazzName((String) map.get("daoImplClazzName"));
	// dao.setDaoInterfacelPackage((String) map.get("daoPackageName"));
	// dao.setDaoInterfaceName((String) map.get("daoClazzName"));
	// dao.setExplain((String) map.get("explainDaoImpl"));
	// this.setGeneric(dao);
	// return dao;
	// }

	/**
	 * 添加字段数据
	 * 
	 * @param model
	 *            model层摸model
	 */
	private void setFieldData(ModelModel model) {
		List<FieldModel> fields = new ArrayList<>();
		String tableName = model.getTableName();
		try {
			columns = ReadDB.getColumns(null, tableName);
			this.columnModel2FieldModel(columns, fields);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setFields(fields);
	}

	/**
	 * columnModel转换成FieldModel
	 * 
	 * @param columns
	 * @param fields
	 * @author XiaoY
	 * @date: 2017年6月25日 下午5:36:04
	 */
	private void columnModel2FieldModel(Map<String, ColumnModel> columns, List<FieldModel> fields) {
		// 必须至少有一个主键
		boolean pk = false;
		Set<String> keySet = columns.keySet();
		for (String key : keySet) {
			ColumnModel columnModel = columns.get(key);
			FieldModel field = new FieldModel();
			String fieldName = "";
			String columnName = columnModel.getColumnName();
			int indexOf = StringUtils.indexOf(columnName, "_", 1);
			if (indexOf > -1) {
				String[] split = columnName.split("_");
				if (split.length > 1) {
					StringBuffer buffer = new StringBuffer(split[0]);
					for (int i = 1; i < split.length; i++) {
						buffer.append(StringUtils.upperCaseFirstChar(split[i]));
					}
					fieldName = buffer.toString();
				} else {
					fieldName = split[0];
				}
			} else {
				fieldName = columnName;
			}
			if (columnModel.getIsPk()) {
				pk = true;
			}
			columnModel.setFieldName(fieldName);
			field.setFieldName(fieldName);
			String javaType = this.sqlType2javaType(columnModel.getColumnType());
			field.setFieldType(javaType);
			field.setRemarks(columnModel.getRemarks());
			fields.add(field);
		}
		if (!pk) {
			throw new RuntimeException("必须至少有一个主键！");
		}
	}

	/**
	 * 类型转换器，sql类型的转换成java类型
	 * 
	 * @param sqlType
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月25日 下午6:12:54
	 */
	private String sqlType2javaType(String sqlType) {
		String javaType = "";
		switch (sqlType) {
			case "INTEGER":
			case "INT":
				javaType = "Integer";
				break;
			case "CHAR":
			case "VARCHAR":
				javaType = "String";
				break;
			case "DATETIME":
				javaType = "Date";
				break;
			case "FLOAT":
				javaType = "Float";
			case "DOUBLE":
				javaType = "Double";
				break;
			case "decimal":
				javaType = "BigDecimal";
				break;
			default:
				break;
		}
		return javaType;
	}

	/**
	 * 添加基础数据
	 * 
	 * @param bm
	 *            BaseModel
	 */
	private void setBaseData(BaseModel bm) {
		bm.setAuthorMail((String) map.get("authorMail"));
		bm.setAuthorName((String) map.get("authorName"));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FROMATE);
		bm.setDate(simpleDateFormat.format(new Date()));
		// bm.setOutPath((String) map.get("outPath"));
		bm.setVersion((String) map.get("version"));
	}

	/**
	 * 添加泛型
	 * 
	 * @param om
	 */
	private void setGeneric(OtherModel om) {
		om.setGenericName((String) map.get("modelClazzName"));
		om.setGenericPackage((String) map.get("modelPackageName"));
	}

	public ModelModel getModel() {
		return model;
	}

	public void setModel(ModelModel model) {
		this.model = model;
	}

	public OtherModel getController() {
		return controller;
	}

	public void setController(OtherModel controller) {
		this.controller = controller;
	}

	public OtherModel getService() {
		return service;
	}

	public void setService(OtherModel service) {
		this.service = service;
	}

	public OtherModel getDao() {
		return dao;
	}

	public void setDao(OtherModel dao) {
		this.dao = dao;
	}

	public OtherModel getServiceImpl() {
		return serviceImpl;
	}

	public void setServiceImpl(OtherModel serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	public ModelModel getModelBase() {
		return modelBase;
	}

	public void setModelBase(ModelModel modelBase) {
		this.modelBase = modelBase;
	}

	public MapperDaoModel getMapperDao() {
		return mapperDao;
	}

	public void setMapperDao(MapperDaoModel mapperDao) {
		this.mapperDao = mapperDao;
	}

	// public OtherModel getDaoImpl() {
	// return daoImpl;
	// }
	//
	// public void setDaoImpl(OtherModel daoImpl) {
	// this.daoImpl = daoImpl;
	// }
}
