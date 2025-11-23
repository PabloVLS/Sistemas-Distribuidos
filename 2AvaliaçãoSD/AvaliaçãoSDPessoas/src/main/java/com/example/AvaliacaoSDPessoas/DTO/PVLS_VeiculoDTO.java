package com.example.AvaliacaoSDPessoas.DTO;

public class PVLS_VeiculoDTO {
    private Long id;
    private String modelo;
    private Integer ano;
    private String placa;

    public PVLS_VeiculoDTO() {}

    public PVLS_VeiculoDTO(Long id, String modelo, Integer ano, String placa) {
        this.id = id;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
