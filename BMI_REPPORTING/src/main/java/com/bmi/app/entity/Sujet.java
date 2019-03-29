package com.bmi.app.entity;

import java.io.Serializable;

import javax.persistence.*;
@Entity
public class Sujet implements Serializable {
	
	@Id
	private String sujetType;
	private String sujetNom;
	public String getSujetType() {
		return sujetType;
	}
	public void setSujetType(String sujetType) {
		this.sujetType = sujetType;
	}
	public String getSujetNom() {
		return sujetNom;
	}
	public void setSujetNom(String sujetNom) {
		this.sujetNom = sujetNom;
	}
	

}
