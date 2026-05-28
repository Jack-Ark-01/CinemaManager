package com.cinemamanager.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class ProiezioneStandard extends Proiezione{

	public ProiezioneStandard(int id, Sala sala, Film film, LocalDate data, LocalTime oraInizio, double prezzobase,
			Set<String> tag) {
		super(id, sala, film, data, oraInizio, prezzobase, tag);
		
	}
	
	
	
	
	@Override
	public LocalDateTime getDataOraInizio() {
		
		return LocalDateTime.of(getData(), getOraInizio());
	}

	@Override
	public double calcolaPrezzoFinale() {
		double prezzoFinale;
		prezzoFinale = getPrezzoBase();
		if(isNelWeekEnd()) prezzoFinale+=2.00;
		if(isSerale()) prezzoFinale+=1.50;
		return prezzoFinale;
	}


	@Override
	public String getTipoProiezione() {
		return "proiezione standard";
	}
	
	


}
