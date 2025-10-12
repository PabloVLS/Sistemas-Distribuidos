package com.sd.prj_planta_serv_1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.prj_planta_serv_1.dtos.FlorRecordDto;
import com.sd.prj_planta_serv_1.models.Flor;
import com.sd.prj_planta_serv_1.services.FlorService;

@RestController
@RequestMapping("/api/flores")
public class FlorController {

    @Autowired
    private FlorService florService; 

    @CrossOrigin(origins = "*")
    @GetMapping
    public List<Flor> getFlores() {
        return florService.getFlores();
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public Optional<Flor> getFlores(@PathVariable Long id) {
        return florService.getFlor(id);
    }


    @CrossOrigin(origins = "*")
    @PostMapping
    public Flor salvarFlor(@RequestBody FlorRecordDto florRecordDto) {
        return florService.salvarFlor(florRecordDto);
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping
    public void deletarFlor(@RequestBody Flor flor) {
        florService.excluirFlor(flor);
    }
}
