package com.cinemamanager.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class Proiezione3D extends Proiezione{
	
	
	private double supplemento3D;
	private boolean occhialiInclusi;
	
	
	public Proiezione3D(int id, Sala sala, Film film, LocalDate data, LocalTime oraInizio, double prezzobase,
			Set<String> tag) {
		super(id, sala, film, data, oraInizio, prezzobase, tag);
		setSupplemento3D(prezzobase);
		setOcchialiInclusi(occhialiInclusi);
		
	}
	
	@Override
	public LocalDateTime getDataOraInizio() {
		return null;
	}

	@Override
	public double calcolaPrezzoFinale() {
		
		double prezzoFinale;
		
		if(contieneTag("3d")) {
			prezzoFinale= getPrezzoBase()+supplemento3D;//suppleento 3d
			if(isNelWeekEnd()) prezzoFinale+=2.00;
			if (occhialiInclusi)prezzoFinale+=1.00;
			return prezzoFinale;
			
		}
		else {
			System.out.println("errore la sala proiezione 3d non è supportata");
			return -1;
		}
	}

	public double getSupplemento3D() {
		return supplemento3D;
	}

	public void setSupplemento3D(double supplemento3d) {
		supplemento3D = supplemento3d;
	}

	public boolean isOcchialiInclusi() {
		return occhialiInclusi;
	}

	public void setOcchialiInclusi(boolean occhialiInclusi) {
		this.occhialiInclusi = occhialiInclusi;
	}
	@Override
	public String getTipoProiezione() {
		return "proiezione 3d";
	}
}
