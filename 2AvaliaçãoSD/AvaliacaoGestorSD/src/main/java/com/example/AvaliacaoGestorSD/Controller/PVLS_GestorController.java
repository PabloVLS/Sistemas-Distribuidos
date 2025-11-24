package com.example.AvaliacaoGestorSD.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.example.AvaliacaoGestorSD.DTO.PVLS_PessoaDTO;
import com.example.AvaliacaoGestorSD.DTO.PVLS_VeiculoDTO;
import com.example.AvaliacaoGestorSD.DTO.PVLS_VinculoDTO;
import com.example.AvaliacaoGestorSD.DTO.PVLS_PessoaComVeiculosDTO;

@RestController
@RequestMapping("/gestor")
@CrossOrigin("*")
public class PVLS_GestorController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${pessoas.url}")
    private String URL_SERVICO_PESSOAS;

    @Value("${veiculos.url}")
    private String URL_SERVICO_VEICULOS;

    // ============ ENDPOINTS DE PESSOAS ============

    @GetMapping("/pessoas")
    public List<PVLS_PessoaDTO> listarPessoas() {
        PVLS_PessoaDTO[] pessoas = restTemplate.getForObject(
            URL_SERVICO_PESSOAS + "/pessoas", 
            PVLS_PessoaDTO[].class
        );
        return Arrays.asList(pessoas);
    }

    @GetMapping("/pessoas/{id}")
    public PVLS_PessoaDTO buscarPessoa(@PathVariable Long id) {
        return restTemplate.getForObject(
            URL_SERVICO_PESSOAS + "/pessoas/" + id, 
            PVLS_PessoaDTO.class
        );
    }

    @PostMapping("/pessoas")
    public PVLS_PessoaDTO salvarPessoa(@RequestBody PVLS_PessoaDTO pessoa) {
        return restTemplate.postForObject(
            URL_SERVICO_PESSOAS + "/pessoas", 
            pessoa, 
            PVLS_PessoaDTO.class
        );
    }

    @PutMapping("/pessoas/{id}")
    public PVLS_PessoaDTO atualizarPessoa(@PathVariable Long id, @RequestBody PVLS_PessoaDTO pessoa) {
        return restTemplate.exchange(
            URL_SERVICO_PESSOAS + "/pessoas/" + id,
            org.springframework.http.HttpMethod.PUT,
            new org.springframework.http.HttpEntity<>(pessoa),
            PVLS_PessoaDTO.class
        ).getBody();
    }

    @DeleteMapping("/pessoas/{id}")
    public void excluirPessoa(@PathVariable Long id) {
        restTemplate.delete(URL_SERVICO_PESSOAS + "/pessoas/" + id);
    }

    // ============ ENDPOINTS DE VEÍCULOS ============

    @GetMapping("/veiculos")
    public List<PVLS_VeiculoDTO> listarVeiculos() {
        PVLS_VeiculoDTO[] veiculos = restTemplate.getForObject(
            URL_SERVICO_VEICULOS + "/veiculos", 
            PVLS_VeiculoDTO[].class
        );
        return Arrays.asList(veiculos);
    }

    @GetMapping("/veiculos/{id}")
    public PVLS_VeiculoDTO buscarVeiculo(@PathVariable Long id) {
        return restTemplate.getForObject(
            URL_SERVICO_VEICULOS + "/veiculos/" + id, 
            PVLS_VeiculoDTO.class
        );
    }

    @PostMapping("/veiculos")
    public PVLS_VeiculoDTO salvarVeiculo(@RequestBody PVLS_VeiculoDTO veiculo) {
        return restTemplate.postForObject(
            URL_SERVICO_VEICULOS + "/veiculos", 
            veiculo, 
            PVLS_VeiculoDTO.class
        );
    }

    @PutMapping("/veiculos/{id}")
    public PVLS_VeiculoDTO atualizarVeiculo(@PathVariable Long id, @RequestBody PVLS_VeiculoDTO veiculo) {
        return restTemplate.exchange(
            URL_SERVICO_VEICULOS + "/veiculos/" + id,
            org.springframework.http.HttpMethod.PUT,
            new org.springframework.http.HttpEntity<>(veiculo),
            PVLS_VeiculoDTO.class
        ).getBody();
    }

    @DeleteMapping("/veiculos/{id}")
    public void excluirVeiculo(@PathVariable Long id) {
        restTemplate.delete(URL_SERVICO_VEICULOS + "/veiculos/" + id);
    }

    // ============ ENDPOINT DE VINCULAÇÃO ============

    @PostMapping("/vincular/{idPessoa}/{idVeiculo}")
    public PVLS_VinculoDTO vincularVeiculo(@PathVariable Long idPessoa, @PathVariable Long idVeiculo) {
        return restTemplate.postForObject(
            URL_SERVICO_PESSOAS + "/pessoas/" + idPessoa + "/veiculo/" + idVeiculo,
            null,
            PVLS_VinculoDTO.class
        );
    }

    @DeleteMapping("/vinculo/{idPessoa}/{idVeiculo}")
    public void desvincularVeiculo(@PathVariable Long idPessoa, @PathVariable Long idVeiculo) {
        restTemplate.delete(URL_SERVICO_PESSOAS + "/pessoas/" + idPessoa + "/veiculo/" + idVeiculo);
    }

    // ============ ENDPOINT DE RELATÓRIO ============

    @GetMapping("/relatorio")
    public List<Map<String, Object>> gerarRelatorio() {
        List<Map<String, Object>> resultado = new ArrayList<>();
        
        try {
            // Buscar todas as pessoas
            PVLS_PessoaDTO[] pessoasArray = restTemplate.getForObject(
                URL_SERVICO_PESSOAS + "/pessoas", 
                PVLS_PessoaDTO[].class
            );
            List<PVLS_PessoaDTO> pessoas = Arrays.asList(pessoasArray != null ? pessoasArray : new PVLS_PessoaDTO[0]);
            
            // Buscar todos os veículos (cache para busca)
            PVLS_VeiculoDTO[] veiculosArray = restTemplate.getForObject(
                URL_SERVICO_VEICULOS + "/veiculos", 
                PVLS_VeiculoDTO[].class
            );
            Map<Long, PVLS_VeiculoDTO> veiculosMap = new HashMap<>();
            if (veiculosArray != null) {
                for (PVLS_VeiculoDTO v : veiculosArray) {
                    veiculosMap.put(v.getId(), v);
                }
            }
            
            // Para cada pessoa, buscar seus veículos vinculados do serviço de pessoas
            for (PVLS_PessoaDTO pessoa : pessoas) {
                try {
                    // Buscar pessoa completa (com veículos vinculados) do serviço de pessoas
                    PVLS_PessoaComVeiculosDTO pessoaComVeiculos = restTemplate.getForObject(
                        URL_SERVICO_PESSOAS + "/pessoas/" + pessoa.getId(),
                        PVLS_PessoaComVeiculosDTO.class
                    );
                    
                    // Se a pessoa tem veículos vinculados, adiciona ao relatório
                    if (pessoaComVeiculos != null && pessoaComVeiculos.getVeiculos() != null && !pessoaComVeiculos.getVeiculos().isEmpty()) {
                        for (PVLS_VeiculoDTO veiculo : pessoaComVeiculos.getVeiculos()) {
                            Map<String, Object> row = new HashMap<>();
                            row.put("pessoaId", pessoa.getId());
                            row.put("pessoaNome", pessoa.getNome());
                            row.put("pessoaCpf", pessoa.getCpf());
                            row.put("veiculoId", veiculo.getId());
                            row.put("veiculoModelo", veiculo.getModelo());
                            row.put("veiculoAno", veiculo.getAno());
                            row.put("veiculoPlaca", veiculo.getPlaca());
                            resultado.add(row);
                        }
                    }
                    // Se pessoa não tem veículos, não adiciona ao relatório
                } catch (Exception e) {
                    // Se falhar ao buscar pessoa com veículos, não adiciona ao relatório
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return resultado;
    }
}
