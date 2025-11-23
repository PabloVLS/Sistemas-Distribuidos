package com.example.AvaliacaoSDVeiculo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.AvaliacaoSDVeiculo.Model.PVLS_Veiculo;

@Repository
public interface PVLS_VeiculoRepository extends JpaRepository<PVLS_Veiculo, Long> {
}
