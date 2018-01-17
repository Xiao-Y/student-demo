package ${packageName};   

import javax.annotation.Resource;

import ${serviceInterfacelPackage}.${serviceInterfaceName};
import ${daoInterfacelPackage}.${daoInterfaceName};
import ${genericPackage}.${genericName};
import org.billow.dao.base.BaseDao;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 <#if explain?exists>
 * ${explain}<br>
 </#if>
 *
 * @version ${version}
 * @author ${authorName}<br>
 * @Mail ${authorMail}<br>
 * @date ${date}
 */
@Service
public class ${clazzName} extends BaseServiceImpl<${genericName}> implements ${serviceInterfaceName} { 

	@Resource
	private ${daoInterfaceName} ${daoInterfaceName ? uncap_first};
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<${genericName}> baseDao) {
		super.baseDao = this.${daoInterfaceName ? uncap_first};
	}
}    