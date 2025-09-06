package com.mycompany.prjaviaoservlet.servlets;

/**
 *
 * @author Pablo
 */
public class Aviao {

    private int id;
    private String modelo;
    private String fabricante;
    private int capacidade;

    public Aviao() {
    }

    public Aviao(String modelo, String fabricante, int capacidade) {
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.capacidade = capacidade;
    }

    public Aviao(int id, String modelo, String fabricante, int capacidade) {
        this.id = id;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.capacidade = capacidade;
    }
    
    

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
