package org.billow.service.system;

import java.util.List;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.apache.commons.lang3.StringUtils;
import org.billow.api.system.ActRepositoryService;
import org.billow.model.custom.DiagramDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class ActRepositoryServiceImpl implements ActRepositoryService {

	@Autowired
	private RepositoryService repositoryService;

	@Override
	public PageInfo<Model> getModel() {
		ModelQuery createModelQuery = repositoryService.createModelQuery();
		long count = createModelQuery.count();
		PageInfo<Model> pageInfo = PageHelper.getPageInfo(count);
		List<Model> list = createModelQuery.orderByLastUpdateTime().desc()
				.listPage(pageInfo.getFirstPage(), pageInfo.getPageSize());
		DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
		for (Model m : list) {
			List<Deployment> deploymentList = deploymentQuery.deploymentName(m.getName()).orderByDeploymenTime().desc()
					.list();
			if (ToolsUtils.isNotEmpty(deploymentList)) {
				Deployment deployment = deploymentList.get(0);
				m.setDeploymentId(deployment.getId());
			}
		}
		pageInfo.setList(list);
		return pageInfo;
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

	@Override
	public byte[] viewPic(String modelId) {
		return repositoryService.getModelEditorSourceExtra(modelId);
	}

	@Override
	public void deleteModel(String modelId) throws Exception {
		repositoryService.deleteModel(modelId);
	}
}
