package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Pessoa;
import services.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @GetMapping
    public List<Pessoa> getAllPessoas() {
        return pessoaService.getPessoas();
    }


    @PostMapping
    public Pessoa savePessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.savePessoa(pessoa);
    }
}
