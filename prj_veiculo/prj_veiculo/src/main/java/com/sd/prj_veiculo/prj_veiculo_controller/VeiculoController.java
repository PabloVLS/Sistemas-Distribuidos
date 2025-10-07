package com.sd.prj_veiculo.prj_veiculo_controller;

import java.net.URI;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.prj_veiculo.prj_veiculo_model.Veiculo;
import com.sd.prj_veiculo.prj_veiculo_service.VeiculoService;



@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

	private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<Veiculo> getAllVeiculos() {
        return veiculoService.getVeiculo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        Veiculo v = veiculoService.getById(id);
        return ResponseEntity.ok(v);
    }

    @PostMapping
    public ResponseEntity<Veiculo> saveVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo saved = veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.created(URI.create("/api/veiculos/" + saved.getIdveiculo())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Veiculo updated = veiculoService.updateVeiculo(id, veiculo);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}