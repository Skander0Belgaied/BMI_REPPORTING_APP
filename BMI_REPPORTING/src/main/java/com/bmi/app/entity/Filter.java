package com.bmi.app.entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Filter implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="filter_id")
	private Long filterId;
	@Column(name="filter_nom",unique=true)
	private String filterNom;
	@Column(name="filter_sql",unique=true)
	private String filterSql;
	

	public Filter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Filter(Long filterId, String filterNom, String filterSql) {
		super();
		this.filterId = filterId;
		this.filterNom = filterNom;
		this.filterSql = filterSql;
	}
	public Filter(String string, String string2) {
		this.filterNom = filterNom;
		this.filterSql = filterSql;
	}
	public Long getFilterId() {
		return filterId;
	}
	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}
	public String getFilterNom() {
		return filterNom;
	}
	public void setFilterNom(String filterNom) {
		this.filterNom = filterNom;
	}
	public String getFilterSql() {
		return filterSql;
	}
	public void setFilterSql(String filterSql) {
		this.filterSql = filterSql;
	}
	

}
