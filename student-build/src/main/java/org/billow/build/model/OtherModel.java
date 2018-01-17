package org.billow.build.model;

/**
 * Controller,Service,Dao层model
 * 
 * @author Liuyongtao
 * @version $Id: ServiceModel.java 2016年1月13日 上午10:31:34 $
 */
public class OtherModel extends BaseModel {
	// dao接口名称
	private String daoInterfaceName;
	// dao接口包
	private String daoInterfacelPackage;
	// service接口名称
	private String serviceInterfaceName;
	// service接口包
	private String serviceInterfacelPackage;
	// 泛型名称
	private String genericName;
	// 泛型包
	private String genericPackage;

	public String getDaoInterfaceName() {
		return daoInterfaceName;
	}

	public void setDaoInterfaceName(String daoInterfaceName) {
		this.daoInterfaceName = daoInterfaceName;
	}

	public String getDaoInterfacelPackage() {
		return daoInterfacelPackage;
	}

	public void setDaoInterfacelPackage(String daointerfacelpackage) {
		this.daoInterfacelPackage = daointerfacelpackage;
	}

	public String getServiceInterfaceName() {
		return serviceInterfaceName;
	}

	public void setServiceInterfaceName(String serviceInterfaceName) {
		this.serviceInterfaceName = serviceInterfaceName;
	}

	public String getServiceInterfacelPackage() {
		return serviceInterfacelPackage;
	}

	public void setServiceInterfacelPackage(String serviceInterfacelPackage) {
		this.serviceInterfacelPackage = serviceInterfacelPackage;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getGenericPackage() {
		return genericPackage;
	}

	public void setGenericPackage(String genericPackage) {
		this.genericPackage = genericPackage;
	}

}
