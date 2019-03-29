package com.bmi.app.entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Filter implements Serializable {

	@Id
	@GeneratedValue
	private Long filterId;
	private String filterNom;
	
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


}
