package org.billow.common.activiti.custom;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.billow.common.activiti.custom.manager.CustomUserEntityManager;

public class CustomUserEntityManagerFactory implements SessionFactory {

	// 使用自定义的User管理类
	private CustomUserEntityManager customUserEntityManager;

	@Override
	public Class<?> getSessionType() {
		// 注意此处也必须为Activiti原生类
		return UserIdentityManager.class;
	}

	@Override
	public Session openSession() {
		return customUserEntityManager;
	}

	public void setCustomUserEntityManager(CustomUserEntityManager customUserEntityManager) {
		this.customUserEntityManager = customUserEntityManager;
	}
}
