package com.bmi.app.entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Rapport implements Serializable {

	@Id
	@GeneratedValue
	private Long rapportId;
	private String rapportNom;
	private String rapportUrl;
	public Long getRapportid() {
		return rapportId;
	}
	public void setRapportid(Long rapportid) {
		this.rapportId = rapportid;
	}
	public String getRapportNomd() {
		return rapportNom;
	}
	public void setRapportNomd(String rapportNomd) {
		this.rapportNom = rapportNomd;
	}
	public String getRapportUrl() {
		return rapportUrl;
	}
	public void setRapportUrl(String rapportUrl) {
		this.rapportUrl = rapportUrl;
	}
	
}
