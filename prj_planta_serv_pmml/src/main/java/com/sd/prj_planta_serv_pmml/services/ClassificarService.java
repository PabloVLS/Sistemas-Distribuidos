package com.sd.prj_planta_serv_pmml.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import org.pmml4s.model.Model;


import com.sd.prj_planta_serv_pmml.dtos.ClasseRecorDto;
import com.sd.prj_planta_serv_pmml.dtos.FlorRecordDto;

@Service
public class ClassificarService {

    public ClasseRecorDto classificar(FlorRecordDto florRecordDto) {
        FileInputStream is = null;
        Map<String, Object> saida = null;

        try {

        	if (florRecordDto.classifica().toLowerCase().equals("svm")) {
        	    is = new FileInputStream("C:\\Users\\Pichau\\Desktop\\Outros\\Aula 1 - Sistemas Distribuidos\\knimeSvmIris.pmml");
        	} else if (florRecordDto.classifica().toLowerCase().equals("decision tree")) {
        	    is = new FileInputStream("C:\\Users\\Pichau\\Desktop\\Outros\\Aula 1 - Sistemas Distribuidos\\knimeDtIris.pmml");
        	} else {
        	    is = new FileInputStream("C:\\Users\\Pichau\\Desktop\\Outros\\Aula 1 - Sistemas Distribuidos\\knimeDtIris.pmml");
        	}
            Model model = Model.fromInputStream(is);

            saida = model.predict(new HashMap<String, Object>() {{
                put("SepalLengthCm", florRecordDto.comprimento_sepala());
                put("SepalWidthCm", florRecordDto.largura_sepala());
                put("PetalLengthCm", florRecordDto.comprimento_petala());
                put("PetalWidthCm", florRecordDto.largula_petala());
            }});



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        String classe = saida.get("predicted_Species").toString();
        if(classe.contains(".")) classe = classe.replace(".", "");
        if(classe.contains("Iris")) classe = classe.replace("Iris", "");
        ClasseRecorDto classeRecorDto = new ClasseRecorDto(classe);
        
        return classeRecorDto;
    }
}
