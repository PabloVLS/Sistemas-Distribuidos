package com.sd.prj_planta_serv_1.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "flor")
public class Flor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idflor;

    @Column(nullable = false)
    private Double comprimentoSepala;

    @Column(nullable = false)
    private Double larguraSepala;

    @Column(nullable = false)
    private Double comprimentoPetala;

    @Column(nullable = false)
    private Double larguraPetala;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private String especieTipo;
    
    
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "idplanta", nullable = false)
    private Planta planta;


	public Long getIdflor() {
		return idflor;
	}


	public void setIdflor(Long idflor) {
		this.idflor = idflor;
	}


	public Double getComprimentoSepala() {
		return comprimentoSepala;
	}


	public void setComprimentoSepala(Double comprimentoSepala) {
		this.comprimentoSepala = comprimentoSepala;
	}


	public Double getLarguraSepala() {
		return larguraSepala;
	}


	public void setLarguraSepala(Double larguraSepala) {
		this.larguraSepala = larguraSepala;
	}


	public Double getComprimentoPetala() {
		return comprimentoPetala;
	}


	public void setComprimentoPetala(Double comprimentoPetala) {
		this.comprimentoPetala = comprimentoPetala;
	}


	public Double getLarguraPetala() {
		return larguraPetala;
	}


	public void setLarguraPetala(Double larguraPetala) {
		this.larguraPetala = larguraPetala;
	}


	public String getCor() {
		return cor;
	}


	public void setCor(String cor) {
		this.cor = cor;
	}


	public String getEspecieTipo() {
		return especieTipo;
	}


	public void setEspecieTipo(String especieTipo) {
		this.especieTipo = especieTipo;
	}


	public Planta getPlanta() {
		return planta;
	}


	public void setPlanta(Planta planta) {
		this.planta = planta;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
