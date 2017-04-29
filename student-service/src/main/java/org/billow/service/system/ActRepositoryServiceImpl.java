package org.billow.service.system;

import java.util.List;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.billow.api.system.ActRepositoryService;
import org.billow.model.custom.DiagramDto;
import org.billow.utils.RequestUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class ActRepositoryServiceImpl implements ActRepositoryService {

	@Autowired
	private RepositoryService repositoryService;

	public PageInfo<Model> getModel() {
		int pageSize = RequestUtils.getPageSize();
		int pageNum = RequestUtils.getTargetPage();
		int firstResult = this.startResult(pageNum, pageSize);
		ModelQuery createModelQuery = repositoryService.createModelQuery();
		List<Model> list = createModelQuery.orderByLastUpdateTime().desc().listPage(firstResult, pageSize);
		long count = createModelQuery.count();
		int pages = this.pages(count, pageSize);
		PageInfo<Model> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPages(pages);
		pageInfo.setPageNum(pageNum);
		pageInfo.setTotal(count);
		return pageInfo;
	}

	/**
	 * 获取页面大小
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param count
	 * @param pageSize
	 * @return
	 * 
	 * @date 2017年4月29日 下午5:36:31
	 */
	private int pages(long count, int pageSize) {
		return (int) ((count % pageSize) == 0 ? (count / pageSize) : ((count / pageSize) + 1));
	}

	/**
	 * 数据起始位置
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param targetPage
	 * @param pageSize
	 * @return
	 * 
	 * @date 2017年4月29日 下午5:45:08
	 */
	private int startResult(int targetPage, int pageSize) {
		return (targetPage - 1) * pageSize;
	}

	@Override
	public Model createModel(DiagramDto diagram) throws Exception {
		String key = diagram.getKey();
		String name = diagram.getName();
		String description = diagram.getDescription();

		ObjectMapper objectMapper = new ObjectMapper();

		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");

		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.put("stencilset", stencilSetNode);

		Model modelData = repositoryService.newModel();
		
		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(description));
		
		modelData.setMetaInfo(modelObjectNode.toString());
		modelData.setName(name);
		modelData.setKey(StringUtils.defaultString(key));
		repositoryService.saveModel(modelData);

		repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
		return modelData;
	}
}
