package org.billow.model.domain;

import java.io.Serializable;

import org.billow.model.base.BaseModel;

public class RoleBase extends BaseModel implements Serializable {

	private static final long serialVersionUID = -2450232790383263014L;

	private Integer id;

	private String rolename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename == null ? null : rolename.trim();
	}
}