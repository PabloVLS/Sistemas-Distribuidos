package com.example.AvaliacaoGestorSD.DTO;

public class PVLS_VinculoDTO {
    private PVLS_PessoaVeiculoIdDTO id;
    private Long idPessoa;
    private Long idVeiculo;

    public PVLS_VinculoDTO() {}

    public PVLS_VinculoDTO(PVLS_PessoaVeiculoIdDTO id, Long idPessoa, Long idVeiculo) {
        this.id = id;
        this.idPessoa = idPessoa;
        this.idVeiculo = idVeiculo;
    }

    public PVLS_PessoaVeiculoIdDTO getId() {
        return id;
    }

    public void setId(PVLS_PessoaVeiculoIdDTO id) {
        this.id = id;
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
