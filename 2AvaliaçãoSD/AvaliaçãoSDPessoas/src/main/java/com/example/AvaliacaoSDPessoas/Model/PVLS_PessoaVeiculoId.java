package com.example.AvaliacaoSDPessoas.Model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PVLS_PessoaVeiculoId implements Serializable {

    private Long idPessoa;
    private Long idVeiculo;

    public PVLS_PessoaVeiculoId() {}

    public PVLS_PessoaVeiculoId(Long idPessoa, Long idVeiculo) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PVLS_PessoaVeiculoId that = (PVLS_PessoaVeiculoId) o;
        return Objects.equals(idPessoa, that.idPessoa) && Objects.equals(idVeiculo, that.idVeiculo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPessoa, idVeiculo);
    }
}
