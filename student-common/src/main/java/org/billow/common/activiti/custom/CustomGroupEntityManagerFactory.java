package org.billow.common.activiti.custom;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.billow.common.activiti.custom.manager.CustomGroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomGroupEntityManagerFactory implements SessionFactory {

	@Autowired
	private CustomGroupEntityManager customGroupEntityManager;

	@Override
	public Class<?> getSessionType() {
		// 返回原始的GroupManager类型
		return GroupEntityManager.class;
	}

	@Override
	public Session openSession() {
		// 返回自定义的GroupManager实例
		return customGroupEntityManager;
	}

}
