package com.example.AvaliacaoSDVeiculo.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.AvaliacaoSDVeiculo.Model.PVLS_Veiculo;
import com.example.AvaliacaoSDVeiculo.Repository.PVLS_VeiculoRepository;

@Service
public class PVLS_VeiculoService {

    @Autowired
    private PVLS_VeiculoRepository PVLS_repositorio;

    public PVLS_Veiculo PVLS_SalvarVeiculo(PVLS_Veiculo PVLS_veiculo) {
        return PVLS_repositorio.save(PVLS_veiculo);
    }

    public void PVLS_ExcluirVeiculo(Long PVLS_id) {
        PVLS_repositorio.deleteById(PVLS_id);
    }

    public List<PVLS_Veiculo> PVLS_ListarVeiculos() {
        return PVLS_repositorio.findAll();
    }

    public PVLS_Veiculo PVLS_BuscarVeiculo(Long PVLS_id) {
        return PVLS_repositorio.findById(PVLS_id).orElse(null);
    }
}
