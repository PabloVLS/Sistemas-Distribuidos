package com.sd.prj_planta_serv_1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sd.prj_planta_serv_1.dtos.FlorRecordDto;
import com.sd.prj_planta_serv_1.models.Flor;
import com.sd.prj_planta_serv_1.repositories.FlorRepository;
import com.sd.prj_planta_serv_1.repositories.PlantaRepository;

@Service
public class FlorService {

    private final FlorRepository florRepository;
    private final PlantaRepository plantaRepository;


    public FlorService(FlorRepository florRepository, PlantaRepository plantaRepository) {
        this.florRepository = florRepository;
        this.plantaRepository = plantaRepository;
    }


    public List<Flor> getFlores() {
        return florRepository.findAll();
    }

    public Optional<Flor> getFlor(Long id) {

        return florRepository.findById(id);
    }
    

    public Flor salvarFlor(FlorRecordDto florRecordDto) {
        Flor flor = new Flor();

        if (florRecordDto.idFlor() != null) {
            flor.setIdflor(florRecordDto.idFlor());
        }

        flor.setComprimentoSepala(florRecordDto.comprimentoSepala());
        flor.setLarguraSepala(florRecordDto.larguraSepala());
        flor.setComprimentoPetala(florRecordDto.comprimentoPetala());
        flor.setLarguraPetala(florRecordDto.larguraPetala());
        flor.setCor(florRecordDto.cor());
        flor.setEspecieTipo(florRecordDto.especieTipo());


        flor.setPlanta(plantaRepository.findById(florRecordDto.idPlanta()).get());

        return florRepository.save(flor);
    }


    public void excluirFlor(Flor flor) {
        florRepository.deleteById(flor.getIdflor());
    }
}
