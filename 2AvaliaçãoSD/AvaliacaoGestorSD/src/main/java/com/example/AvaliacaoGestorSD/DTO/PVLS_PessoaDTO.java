package com.example.AvaliacaoGestorSD.DTO;

public class PVLS_PessoaDTO {
    private Long id;
    private String nome;
    private String cpf;

    public PVLS_PessoaDTO() {}

    public PVLS_PessoaDTO(Long id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
