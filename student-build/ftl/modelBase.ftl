package ${packageName};  

import java.io.Serializable;
import org.billow.model.base.BaseModel; 
  
/**
 * 
 <#if explain?exists>
 * ${explain}<br>
 </#if>
 *
 * 对应的表名：${tableName}
 * @version ${version}
 * @author ${authorName}<br>
 * @Mail ${authorMail}<br>
 * @date ${date}
 */
public class ${clazzName} extends BaseModel implements Serializable { 
	
	public ${clazzName}() {
		super();
	}
	
	/**
	 * 主键构造器
<#list fields as pro>
	<#if pro.isPK>
	 * @param ${pro.fieldName} <#if pro.remarks?exists>${pro.remarks}</#if>
	</#if>
</#list> 
	 */
	public ${clazzName}(${constructor}) {
		super();
<#list fields as pro>
	<#if pro.isPK>
		this.${pro.fieldName} = ${pro.fieldName};
	</#if>
</#list> 
	}
	
<#list fields as pro>
	<#if pro.remarks?exists>
	// ${pro.remarks}
	</#if>
    private ${pro.fieldType} ${pro.fieldName};  
</#list>  
      
<#list fields as pro> 
	/**
	<#if pro.remarks?exists>
	 * ${pro.remarks}
	</#if>
	 * 
	 * @return
	 * @author ${authorName}<br>
	 * @date: ${date}
	 */
    public ${pro.fieldType} get${pro.fieldName ? cap_first}(){  
        return this.${pro.fieldName};  
    } 
    
    /**
	 <#if pro.remarks?exists>
	 * ${pro.remarks}
	 </#if>
	 * 
	 * @param ${pro.fieldName}
	 * @author ${authorName}<br>
	 * @date: ${date}
	 */
    public void set${pro.fieldName ? cap_first}(${pro.fieldType} ${pro.fieldName}){  
        this.${pro.fieldName}=${pro.fieldName};  
    }  
     
</#list>  

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "${pkToString}";
	}
}  