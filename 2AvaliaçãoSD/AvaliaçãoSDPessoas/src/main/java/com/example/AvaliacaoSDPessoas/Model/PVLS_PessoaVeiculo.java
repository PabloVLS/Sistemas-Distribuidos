package com.example.AvaliacaoSDPessoas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;

@Entity
@Table(name = "pessoa_veiculo")
public class PVLS_PessoaVeiculo {
    @EmbeddedId
    private PVLS_PessoaVeiculoId id;

    public PVLS_PessoaVeiculo() {}

    public PVLS_PessoaVeiculo(PVLS_PessoaVeiculoId id) {
        this.id = id;
    }

    public Long getIdPessoa() {
        return id != null ? id.getIdPessoa() : null;
    }

    public void setIdPessoa(Long idPessoa) {
        if (this.id == null) this.id = new PVLS_PessoaVeiculoId();
        this.id.setIdPessoa(idPessoa);
    }

    public Long getIdVeiculo() {
        return id != null ? id.getIdVeiculo() : null;
    }

    public void setIdVeiculo(Long idVeiculo) {
        if (this.id == null) this.id = new PVLS_PessoaVeiculoId();
        this.id.setIdVeiculo(idVeiculo);
    }

    public PVLS_PessoaVeiculoId getId() {
        return id;
    }

    public void setId(PVLS_PessoaVeiculoId id) {
        this.id = id;
    }
}