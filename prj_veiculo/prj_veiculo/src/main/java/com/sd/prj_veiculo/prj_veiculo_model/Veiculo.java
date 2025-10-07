package com.sd.prj_veiculo.prj_veiculo_model;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Table;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idveiculo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String modelo;
    
    @Column(nullable = false)
    private String marca;

    @Column(name = "ano", nullable = false)
    private int ano;


    public Veiculo() {
    }


    
	public Veiculo(long idveiculo, String nome, String modelo, String marca, int ano) {
		super();
		this.idveiculo = idveiculo;
		this.nome = nome;
		this.modelo = modelo;
		this.marca = marca;
		this.ano = ano;
	}



	public long getIdveiculo() {
		return idveiculo;
	}


	public void setIdveiculo(long idveiculo) {
		this.idveiculo = idveiculo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


   
}