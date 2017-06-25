package ${packageName};  

import java.io.Serializable;
import org.billow.model.base.BaseModel; 
  
/**
 * 
 <#if explain??>
 * ${explain!}<br>
 </#if>
 *
 * 对应的表名：${tableName}
 * @version ${version}
 * @author ${authorName}<br>
 * @Mail ${authorMail}<br>
 * @date ${date}
 */
public class ${clazzName} extends BaseModel implements Serializable { 
 
<#list fields as pro>
	<#if remarks??>
	// ${remarks!}
	</#if>
    private ${pro.fieldType} ${pro.fieldName};  
</#list>  
      
<#list fields as pro> 
	/**
	<#if pro.remarks??>
	 * ${pro.remarks!}
	</#if>
	 * 
	 * @return
	 * @author ${authorName}<br>
	 * @date: ${date}
	 */
    public ${pro.fieldType} get<@upperFC>${pro.fieldName}</@upperFC>(){  
        return this.${pro.fieldName};  
    } 
    
    /**
	 <#if pro.remarks??>
	 * ${pro.remarks!}
	 </#if>
	 * 
	 * @param ${pro.fieldName}
	 * @author ${authorName}<br>
	 * @date: ${date}
	 */
    public void set<@upperFC>${pro.fieldName}</@upperFC>(${pro.fieldType} ${pro.fieldName}){  
        this.${pro.fieldName}=${pro.fieldName};  
    }  
     
</#list>  
}  