package ${packageName};  
  
import ${modelBasePackageName}.${modelBaseClazzName}; 
  
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
public class ${clazzName} extends ${modelBaseClazzName} {
	
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
		super(${constructorNo});
	}
	
	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}  