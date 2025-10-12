package com.sd.prj_planta_serv_1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.prj_planta_serv_1.models.Planta;
import com.sd.prj_planta_serv_1.services.PlantaService;

@RestController
@RequestMapping("/api/plantas")
public class PlantaController { 


    @Autowired
    private PlantaService plantaService; 


    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Planta> getPlantas() {
        return plantaService.getPlantas();
    }

 
    @CrossOrigin(origins = "*")
    @PostMapping
    public Planta salvarPlanta(@RequestBody Planta planta) {
        return plantaService.salvarPlanta(planta);
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping
    public void deletarPlanta(@RequestBody Planta planta) {
        plantaService.excluirPlanta(planta);
    }
}
