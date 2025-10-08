package com.sd.prj_veiculo.prj_veiculo_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sd.prj_veiculo.prj_veiculo_model.Veiculo;
import com.sd.prj_veiculo.prj_veiculo_repository.VeiculoRepository;



@Service
public class VeiculoService {


    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> getVeiculo() {
        return veiculoRepository.findAll();
    }

    public Veiculo saveVeiculo(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo getById(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com id " + id));
    }

    public Veiculo updateVeiculo(Long id, Veiculo updated) {
        Veiculo v = getById(id);
        v.setNome(updated.getNome());
        v.setModelo(updated.getModelo());
        v.setMarca(updated.getMarca());
        v.setAno(updated.getAno());
        return veiculoRepository.save(v);
    }

    public void deleteVeiculo(Long id) {
        Veiculo v = getById(id);
        veiculoRepository.delete(v);
    }
}