package com.sd.java_service_teste;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServicosWeb {

    private static final String CONTEUDO = "Olá, %s!";
    private final AtomicLong contador = new AtomicLong();

    @GetMapping("/pessoa")
    public Pessoa pessoa(@RequestParam(required = false, defaultValue = "Mundo") String nome) {
        System.out.println("Acesso ao método!!!");
        return new Pessoa(contador.incrementAndGet(), String.format(CONTEUDO, nome));
    }
}