package com.dacky.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="domain")
@Entity
public class Domain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	@Column
	private String domainUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDomainUrl() {
		return domainUrl;
	}
	public void setDomainUrl(String domainUrl) {
		this.domainUrl = domainUrl;
	}
	
	
}
