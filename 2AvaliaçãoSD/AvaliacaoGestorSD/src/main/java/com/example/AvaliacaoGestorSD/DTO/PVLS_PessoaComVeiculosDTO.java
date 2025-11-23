package com.example.AvaliacaoGestorSD.DTO;

import java.util.List;

public class PVLS_PessoaComVeiculosDTO {
    private Long id;
    private String nome;
    private String cpf;
    private List<PVLS_VeiculoDTO> veiculos;

    public PVLS_PessoaComVeiculosDTO() {}

    public PVLS_PessoaComVeiculosDTO(Long id, String nome, String cpf, List<PVLS_VeiculoDTO> veiculos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.veiculos = veiculos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<PVLS_VeiculoDTO> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<PVLS_VeiculoDTO> veiculos) {
        this.veiculos = veiculos;
    }
}
