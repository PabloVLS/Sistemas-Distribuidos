package com.example.AvaliacaoSDPessoas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.AvaliacaoSDPessoas.Model.PVLS_Pessoa;

@Repository
public interface PVLS_PessoaRepository extends JpaRepository<PVLS_Pessoa, Long> {
}
