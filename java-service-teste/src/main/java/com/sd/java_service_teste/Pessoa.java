package com.sd.java_service_teste;

public class Pessoa {
    private final long id;
    private final String nome;


    public Pessoa() {
        this.id = -1;
        this.nome = "";
    }


    public Pessoa(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
