package com.prop.mnt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pages")
public class Pages implements Serializable {
    @Id
    @Column(name = "PAGE_ID") 
    private Integer pageId;
	//@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="MODULE_ID")
    //@Column(name="MODULE_ID")
   // private Integer moduleId;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MODULE_ID")
    private Modules module;
    
    @Column(name = "PAGE_NAME")
	private String pageName;
    @Column(name = "PATH")
	private String path;
    
    
	public Pages(Integer pageId, String pageName, String path) {
		//super();
		this.pageId = pageId;
		this.pageName = pageName;
		this.path = path;
	}
	public Pages() {
		super();
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public Modules getModule() {
		return module;
	}
	public void setModule(Modules module) {
		this.module = module;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
    
}
