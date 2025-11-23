package com.example.AvaliacaoGestorSD.DTO;

public class PVLS_PessoaVeiculoIdDTO {
    private Long idPessoa;
    private Long idVeiculo;

    public PVLS_PessoaVeiculoIdDTO() {}

    public PVLS_PessoaVeiculoIdDTO(Long idPessoa, Long idVeiculo) {
        this.idPessoa = idPessoa;
        this.idVeiculo = idVeiculo;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
}
