package org.billow.common.activiti.custom;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.billow.common.activiti.custom.manager.CustomGroupEntityManager;

public class CustomGroupEntityManagerFactory implements SessionFactory {

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

	public void setCustomGroupEntityManager(CustomGroupEntityManager customGroupEntityManager) {
		this.customGroupEntityManager = customGroupEntityManager;
	}

}
