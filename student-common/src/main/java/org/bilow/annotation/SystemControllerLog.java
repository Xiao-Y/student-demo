package org.bilow.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统操作日志记录
 * 
 * @author liuyongtao
 * 
 * @date 2017年1月11日 下午7:20:28
 */
// 说明该注解将被包含在javadoc中
@Documented
// 定义注解的作用目标**作用范围字段、枚举的常量/方法
@Target({ ElementType.METHOD })
// 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {
	/**
	 * 模块名
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年1月11日 下午7:35:17
	 */
	String module();

	/**
	 * 功能
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年1月11日 下午7:35:30
	 */
	String function();

	/**
	 * 操作
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年1月11日 下午7:35:39
	 */
	String operation();

	/**
	 * 备注
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年1月11日 下午7:35:46
	 */
	String note() default "";
}
