package org.billow.api.system;

import org.activiti.engine.repository.Model;
import org.billow.model.custom.DiagramDto;

import com.github.pagehelper.PageInfo;

public interface ActRepositoryService {

	/**
	 * 分页查询出模板列表
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年5月5日 上午9:37:58
	 */
	public PageInfo<Model> getModel();

	/**
	 * 创建模板
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param diagram
	 * @return
	 * @throws Exception
	 * 
	 * @date 2017年5月5日 上午9:38:29
	 */
	public Model createModel(DiagramDto diagram) throws Exception;

	/**
	 * 查看模板流程图
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param modelId
	 *            模板id
	 * @return
	 * 
	 * @date 2017年5月5日 上午9:37:40
	 */
	public byte[] viewPic(String modelId);

	/**
	 * 删除模板
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param modelId
	 *            模板id
	 * @throws Exception
	 * 
	 * @date 2017年5月5日 上午10:09:25
	 */
	public void deleteModel(String modelId) throws Exception;
}
