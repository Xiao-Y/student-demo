package org.billow.api.system;

import org.activiti.engine.repository.Model;
import org.billow.model.custom.DiagramDto;

import com.github.pagehelper.PageInfo;

public interface ActRepositoryService {

	public PageInfo<Model> getModel();

	public Model createModel(DiagramDto diagram) throws Exception;
}
