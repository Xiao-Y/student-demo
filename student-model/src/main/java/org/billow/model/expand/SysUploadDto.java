package org.billow.model.expand;  
  
import org.billow.model.domain.SysUploadBase; 
  
/**
 * 
 * 数据字典model模型<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-15 14:12:02
 */
public class SysUploadDto extends SysUploadBase {
	
	public SysUploadDto() {
		super();
	}
	
	/**
	 * 主键构造器
	 * @param id 主键
	 */
	public SysUploadDto(String id ) {
		super(id );
	}

	/**
	 * 格式化后的文件大小
	 */
	public String fileSizeF;

	/**
	 * 格式化后的文件大小
	 * @return
	 */
	public String getFileSizeF() {
		return fileSizeF;
	}

	/**
	 * 格式化后的文件大小
	 * @param fileSizeF
	 */
	public void setFileSizeF(String fileSizeF) {
		this.fileSizeF = fileSizeF;
	}

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return super.toString();
	}
}  