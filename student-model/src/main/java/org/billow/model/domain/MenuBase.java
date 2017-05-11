package org.billow.model.domain;

import java.io.Serializable;
import java.util.List;

import org.billow.model.base.BaseModel;
import org.billow.model.expand.MenuDto;

public class MenuBase extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8914050685684807538L;

	private Integer id;

	private String title;

	private String icon;

	private Boolean spread;

	private String href;

	private Integer pid;

	private Boolean validind;

	private Double displayno;

	List<MenuDto> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public Boolean getSpread() {
		return spread;
	}

	public void setSpread(Boolean spread) {
		this.spread = spread;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href == null ? null : href.trim();
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Boolean getValidind() {
		return validind;
	}

	public void setValidind(Boolean validind) {
		this.validind = validind;
	}

	public Double getDisplayno() {
		return displayno;
	}

	public void setDisplayno(Double displayno) {
		this.displayno = displayno;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> childList) {
		this.children = childList;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", title=" + title + ", icon=" + icon + ", spread=" + spread + ", href=" + href
				+ ", pid=" + pid + ", validind=" + validind + ", displayno=" + displayno + "]";
	}
}