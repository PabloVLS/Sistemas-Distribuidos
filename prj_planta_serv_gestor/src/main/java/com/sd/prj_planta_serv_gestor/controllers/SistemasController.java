package com.sd.prj_planta_serv_gestor.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.prj_planta_serv_gestor.services.SistemasServices;

@RestController
@RequestMapping("/api/sistemas")
@CrossOrigin(origins = "*")
public class SistemasController {

    @Autowired
    private SistemasServices sistemasServices;

    /* ======================
       ENDPOINTS PLANTAS
       ====================== */

    @GetMapping("/plantas")
    public ResponseEntity<String> listarPlantas() {
        return sistemasServices.getPlantas();
    }

    @PostMapping("/plantas")
    public ResponseEntity<String> salvarPlanta(@RequestBody String plantaJson) {
        RequestEntity<String> request = RequestEntity.post("").body(plantaJson);
        return sistemasServices.salvarPlanta(request);
    }

    @DeleteMapping("/plantas")
    public ResponseEntity<String> deletarPlanta(@RequestBody String plantaJson) {
        RequestEntity<String> request = RequestEntity
                .method(HttpMethod.DELETE, "http://localhost:8881/api/plantas")
                .body(plantaJson);

        sistemasServices.deletarPlanta(request);
        return ResponseEntity.ok("Planta deletada com sucesso");
    }

    /* ======================
       ENDPOINTS FLORES
       ====================== */

    @GetMapping("/flores")
    public ResponseEntity<String> listarFlores() {
        return sistemasServices.getFlores();
    }

    @PostMapping("/flores")
    public ResponseEntity<String> salvarFlor(@RequestBody String florJson) {
        RequestEntity<String> request = RequestEntity.post("").body(florJson);
        return sistemasServices.salvarFlor(request);
    }

    @DeleteMapping("/flores")
    public ResponseEntity<String> deletarFlor(@RequestBody String florJson) {
        RequestEntity<String> request = RequestEntity
                .method(HttpMethod.DELETE, "http://localhost:8081/api/flores")
                .body(florJson);

        sistemasServices.deletarFlor(request);
        return ResponseEntity.ok("Flor deletada com sucesso");
    }

    /* ======================
       ENDPOINT PMML - CLASSIFICAÇÃO
       ====================== */

    @PostMapping("/classificar")
    public ResponseEntity<String> classificarFlor(@RequestBody String dadosFlorJson) {
        RequestEntity<String> request = RequestEntity
                .post(URI.create("http://localhost:8083/api/pmml"))
                .header("Content-Type", "application/json")
                .body(dadosFlorJson);

        return sistemasServices.classificar(request);
    }
}