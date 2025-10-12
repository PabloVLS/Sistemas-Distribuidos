package com.sd.prj_planta_serv_1.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "planta")
public class Planta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idplanta;
	
	@Column(nullable = false, unique = true)
	private String genero;
	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "planta", fetch = FetchType.LAZY)
	Set<Flor> flores = new HashSet<Flor>();


	public long getIdplanta() {
		return idplanta;
	}


	public void setIdplanta(long idplanta) {
		this.idplanta = idplanta;
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	public Set<Flor> getFlores() {
		return flores;
	}


	public void setFlores(Set<Flor> flores) {
		this.flores = flores;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
