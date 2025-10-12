package com.sd.prj_planta_serv_1.dtos;

public record FlorRecordDto(
	    Long idFlor, 
	    Double comprimentoSepala,
	    Double larguraSepala,
	    Double comprimentoPetala,
	    Double larguraPetala,
	    String cor, 
	    String especieTipo,
	    Long idPlanta 
	) {
	}