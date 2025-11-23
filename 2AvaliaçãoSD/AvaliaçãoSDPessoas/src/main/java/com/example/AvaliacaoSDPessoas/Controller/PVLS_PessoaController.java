package com.example.AvaliacaoSDPessoas.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import com.example.AvaliacaoSDPessoas.Model.PVLS_Pessoa;
import com.example.AvaliacaoSDPessoas.Model.PVLS_PessoaVeiculo;
import com.example.AvaliacaoSDPessoas.Service.PVLS_PessoaService;
import com.example.AvaliacaoSDPessoas.DTO.PVLS_PessoaComVeiculosDTO;
import com.example.AvaliacaoSDPessoas.DTO.PVLS_VeiculoDTO;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin("*")
public class PVLS_PessoaController {

    @Autowired
    private PVLS_PessoaService PVLS_servicePessoa;

    @GetMapping
    public List<PVLS_Pessoa> PVLS_ListarTodas() {
        return PVLS_servicePessoa.PVLS_ListarPessoas();
    }

    @GetMapping("/{id}")
    public PVLS_PessoaComVeiculosDTO PVLS_BuscarPorId(@PathVariable Long id) {
        PVLS_Pessoa pessoa = PVLS_servicePessoa.PVLS_BuscarPessoa(id);
        if (pessoa == null) return null;
        
        List<PVLS_VeiculoDTO> veiculos = PVLS_servicePessoa.PVLS_BuscarVeiculosPorPessoa(id);
        
        return new PVLS_PessoaComVeiculosDTO(
            pessoa.getId(),
            pessoa.getNome(),
            pessoa.getCpf(),
            veiculos
        );
    }

    @PostMapping
    public PVLS_Pessoa PVLS_CriarPessoa(@RequestBody PVLS_Pessoa PVLS_pessoa) {
        return PVLS_servicePessoa.PVLS_SalvarPessoa(PVLS_pessoa);
    }

    @PutMapping("/{id}")
    public PVLS_Pessoa PVLS_AtualizarPessoa(@PathVariable Long id, @RequestBody PVLS_Pessoa PVLS_pessoa) {
        PVLS_pessoa.setId(id);
        return PVLS_servicePessoa.PVLS_SalvarPessoa(PVLS_pessoa);
    }

    @DeleteMapping("/{id}")
    public void PVLS_DeletarPessoa(@PathVariable Long id) {
        PVLS_servicePessoa.PVLS_ExcluirPessoa(id);
    }

    @PostMapping("/{idPessoa}/veiculo/{idVeiculo}")
    public PVLS_PessoaVeiculo PVLS_VincularVeiculo(@PathVariable Long idPessoa, @PathVariable Long idVeiculo) {
        return PVLS_servicePessoa.PVLS_VincularVeiculo(idPessoa, idVeiculo);
    }
}
