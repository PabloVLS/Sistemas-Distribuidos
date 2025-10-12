package com.sd.prj_planta_serv_pmml.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sd.prj_planta_serv_pmml.dtos.ClasseRecorDto;
import com.sd.prj_planta_serv_pmml.dtos.FlorRecordDto;
import com.sd.prj_planta_serv_pmml.services.ClassificarService;

@RestController
@RequestMapping("/api/pmml")
@CrossOrigin(origins = "*")
public class ClassificarController {

    @Autowired
    private ClassificarService classificarService;

    @PostMapping
    public ClasseRecorDto salvarClassificador(@RequestBody FlorRecordDto florRecordDto) {
        return classificarService.classificar(florRecordDto);
    }
}
