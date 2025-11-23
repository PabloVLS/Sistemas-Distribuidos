package com.example.AvaliacaoSDPessoas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.AvaliacaoSDPessoas.Model.PVLS_PessoaVeiculo;
import com.example.AvaliacaoSDPessoas.Model.PVLS_PessoaVeiculoId;

@Repository
public interface PVLS_PessoaVeiculoRepository extends JpaRepository<PVLS_PessoaVeiculo, PVLS_PessoaVeiculoId> {
}