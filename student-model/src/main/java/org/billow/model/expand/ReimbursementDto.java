package org.billow.model.expand;  
  
import org.billow.model.domain.ReimbursementBase; 
  
/**
 * 
 * 报销流程model模型<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-27 17:27:38
 */
public class ReimbursementDto extends ReimbursementBase {
	
	public ReimbursementDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 
	 */
	public ReimbursementDto(Integer id ) {
		super(id );
	}
	
	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}  