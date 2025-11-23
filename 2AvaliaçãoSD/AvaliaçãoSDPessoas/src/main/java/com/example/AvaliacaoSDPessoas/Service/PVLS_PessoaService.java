package com.example.AvaliacaoSDPessoas.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import com.example.AvaliacaoSDPessoas.Model.PVLS_Pessoa;
import com.example.AvaliacaoSDPessoas.Model.PVLS_PessoaVeiculo;
import com.example.AvaliacaoSDPessoas.Repository.PVLS_PessoaRepository;
import com.example.AvaliacaoSDPessoas.Repository.PVLS_PessoaVeiculoRepository;
import com.example.AvaliacaoSDPessoas.DTO.PVLS_VeiculoDTO;
import org.springframework.web.client.RestTemplate;

@Service
public class PVLS_PessoaService {

    @Autowired
    private PVLS_PessoaRepository PVLS_repoPessoa;

    @Autowired
    private PVLS_PessoaVeiculoRepository PVLS_repoVinculo;

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_SERVICO_VEICULOS = "http://localhost:8082";

    public PVLS_Pessoa PVLS_SalvarPessoa(PVLS_Pessoa p) {
        return PVLS_repoPessoa.save(p);
    }

    public void PVLS_ExcluirPessoa(Long id) {
        PVLS_repoPessoa.deleteById(id);
    }

    public List<PVLS_Pessoa> PVLS_ListarPessoas() {
        return PVLS_repoPessoa.findAll();
    }

    public PVLS_Pessoa PVLS_BuscarPessoa(Long id) {
        return PVLS_repoPessoa.findById(id).orElse(null);
    }

    public PVLS_PessoaVeiculo PVLS_VincularVeiculo(Long idPessoa, Long idVeiculo) {
        PVLS_PessoaVeiculo pv = new PVLS_PessoaVeiculo();
        pv.setIdPessoa(idPessoa);
        pv.setIdVeiculo(idVeiculo);
        return PVLS_repoVinculo.save(pv);
    }

    public List<PVLS_VeiculoDTO> PVLS_BuscarVeiculosPorPessoa(Long idPessoa) {
        List<PVLS_VeiculoDTO> veiculosVinculados = new ArrayList<>();
        
        try {
            // Buscar todos os vínculos de pessoa_veiculo para esta pessoa
            List<PVLS_PessoaVeiculo> vinculos = PVLS_repoVinculo.findAll();
            
            for (PVLS_PessoaVeiculo vinculo : vinculos) {
                if (vinculo.getIdPessoa().equals(idPessoa)) {
                    // Buscar o veículo do serviço de veículos
                    try {
                        PVLS_VeiculoDTO veiculo = restTemplate.getForObject(
                            URL_SERVICO_VEICULOS + "/veiculos/" + vinculo.getIdVeiculo(),
                            PVLS_VeiculoDTO.class
                        );
                        if (veiculo != null) {
                            veiculosVinculados.add(veiculo);
                        }
                    } catch (Exception e) {
                        // Se falhar ao buscar o veículo, ignora
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return veiculosVinculados;
    }
}
