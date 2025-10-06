package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Veiculo;
import repository.VeiculoRepository;

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

    public Veiculo saveVeiculo(Veiculo pessoa) {
        return veiculoRepository.save(pessoa);
    }
}