package com.sd.prj_planta_serv_1.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sd.prj_planta_serv_1.models.Planta;
import com.sd.prj_planta_serv_1.repositories.PlantaRepository;

@Service
public class PlantaService {

 
    private final PlantaRepository plantaRepository;

    public PlantaService(PlantaRepository plantaRepository) {
        this.plantaRepository = plantaRepository;
    }


    public List<Planta> getPlantas() {
        return plantaRepository.findAll();
    }


    public Planta salvarPlanta(Planta planta) {
        return plantaRepository.save(planta);
    }


    public void excluirPlanta(Planta planta) {
        plantaRepository.deleteById(planta.getIdplanta());
    }
}
