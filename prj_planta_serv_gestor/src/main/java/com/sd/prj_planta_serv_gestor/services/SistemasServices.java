package com.sd.prj_planta_serv_gestor.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SistemasServices {

    private RestTemplate restTemplate;
    private String urlServiPlantas;
    private String urlServiFlores;
    private String urlServ3Pmml;

    public SistemasServices() {
        this.restTemplate = new RestTemplate();
        this.urlServiPlantas = "http://localhost:8881/api/plantas";
        this.urlServiFlores = "http://localhost:8081/api/flores";
        this.urlServ3Pmml = "http://localhost:8083/api/pmml";
    }

    /* ======================
       Métodos Service - API Plantas
       ====================== */

    public ResponseEntity<String> getPlantas() {
        ResponseEntity<String> response = restTemplate.getForEntity(urlServiPlantas, String.class);
        return response;
    }

    public ResponseEntity<String> salvarPlanta(RequestEntity<String> planta) {
        ResponseEntity<String> response = restTemplate.postForEntity(urlServiPlantas, planta, String.class);
        return response;
    }
    
    public void deletarPlanta(RequestEntity<String> planta) {
        restTemplate.exchange(urlServiPlantas, HttpMethod.DELETE, planta, String.class);
    }

    /* ======================
       Métodos Service - API Flores (opcional)
       ====================== */

    public ResponseEntity<String> getFlores() {
        ResponseEntity<String> response = restTemplate.getForEntity(urlServiFlores, String.class);
        return response;
    }

    public ResponseEntity<String> salvarFlor(RequestEntity<String> flor) {
        ResponseEntity<String> response = restTemplate.postForEntity(urlServiFlores, flor, String.class);
        return response;
    }
    
    public void deletarFlor(RequestEntity<String> flor) {
        restTemplate.exchange(urlServiFlores, HttpMethod.DELETE, flor, String.class);
    }

    /* ======================
       Métodos Service - API PMML
       ====================== */

    public ResponseEntity<String> classificar(RequestEntity<String> dadosflor) {
        ResponseEntity<String> response = restTemplate.postForEntity(urlServ3Pmml, dadosflor, String.class);
        return response;
    }
}