package com.example.AvaliacaoSDVeiculo.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.AvaliacaoSDVeiculo.Model.PVLS_Veiculo;
import com.example.AvaliacaoSDVeiculo.Service.PVLS_VeiculoService;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin("*")
public class PVLS_VeiculoController {

    @Autowired
    private PVLS_VeiculoService PVLS_serviceVeiculo;

    @GetMapping
    public List<PVLS_Veiculo> PVLS_ListarTodos() {
        return PVLS_serviceVeiculo.PVLS_ListarVeiculos();
    }

    @GetMapping("/{id}")
    public PVLS_Veiculo PVLS_BuscarPorId(@PathVariable Long id) {
        return PVLS_serviceVeiculo.PVLS_BuscarVeiculo(id);
    }

    @PostMapping
    public PVLS_Veiculo PVLS_CriarVeiculo(@RequestBody PVLS_Veiculo PVLS_veiculo) {
        return PVLS_serviceVeiculo.PVLS_SalvarVeiculo(PVLS_veiculo);
    }

    @PutMapping("/{id}")
    public PVLS_Veiculo PVLS_AtualizarVeiculo(@PathVariable Long id, @RequestBody PVLS_Veiculo PVLS_veiculo) {
        PVLS_veiculo.setId(id);
        return PVLS_serviceVeiculo.PVLS_SalvarVeiculo(PVLS_veiculo);
    }

    @DeleteMapping("/{id}")
    public void PVLS_DeletarVeiculo(@PathVariable Long id) {
        PVLS_serviceVeiculo.PVLS_ExcluirVeiculo(id);
    }
}
