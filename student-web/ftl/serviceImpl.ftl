package ${packageName};   

import javax.annotation.Resource;

import ${serviceInterfacelPackage}.${serviceInterfaceName};
import ${daoInterfacelPackage}.${daoInterfaceName};
import ${genericPackage}.${genericName};
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

	@SuppressWarnings("unused")
	private ${daoInterfaceName} ${daoInterfaceName ? uncap_first};

	@Resource
	public void set${daoInterfaceName}(${daoInterfaceName} ${daoInterfaceName ? uncap_first}) {
		this.${daoInterfaceName ? uncap_first} = ${daoInterfaceName ? uncap_first};
		super.setBaseDao(${daoInterfaceName ? uncap_first});
	}
}    