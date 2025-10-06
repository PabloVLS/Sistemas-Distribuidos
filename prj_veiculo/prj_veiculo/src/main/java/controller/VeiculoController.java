package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Veiculo;
import service.VeiculoService;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;


    @GetMapping
    public List<Veiculo> getAllVeiculos() {
        return veiculoService.getVeiculo();
    }


    @PostMapping
    public Veiculo saveVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoService.saveVeiculo(veiculo);
    }
}