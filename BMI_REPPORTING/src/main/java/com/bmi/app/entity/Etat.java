package com.bmi.app.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Etat implements Serializable {
	@Id
	private String etatSujet;
	private Long etatFilter;
	private Long etatRapport;
	public String getEtatSujet() {
		return etatSujet;
	}
	public void setEtatSujet(String etatSujet) {
		this.etatSujet = etatSujet;
	}
	public Long getEtatFilter() {
		return etatFilter;
	}
	public void setEtatFilter(Long etatFilter) {
		this.etatFilter = etatFilter;
	}
	public Long getEtatRapport() {
		return etatRapport;
	}
	public void setEtatRapport(Long etatRapport) {
		this.etatRapport = etatRapport;
	}
	
	

}
