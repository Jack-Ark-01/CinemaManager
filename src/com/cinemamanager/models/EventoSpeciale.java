package com.cinemamanager.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

public class EventoSpeciale extends Proiezione{		

	private String nomeEvento;
	private String ospite;
	private boolean postiLimitati;
	
	
	
	public EventoSpeciale(int id, Sala sala, Film film, LocalDate data, LocalTime oraInizio, double prezzobase,
			              Set<String> tag, String nomeEvento, String ospite, boolean postiLimitati) {
		
		super(id, sala, film, data, oraInizio, prezzobase, tag);
		
		setNomeEvento(nomeEvento);
		setOspite(ospite);
		setPostiLimitati(postiLimitati);
	}


	//COSTRUTTORE PER LETTURA FILE
	public EventoSpeciale(int idProiezione, Film film, Sala sala, LocalDate data, LocalTime oraInizio, double prezzobase, String nomeEvento, String ospite, boolean postiLimitati, Set<String> tag) {
		super(idProiezione, film, sala, data, oraInizio, prezzobase, tag);
		setNomeEvento(nomeEvento);
		setOspite(ospite);
		setPostiLimitati(postiLimitati);
	}
	
	
	@Override
	public LocalDateTime getDataOraInizio() {
		
		return LocalDateTime.of(getData(), getOraInizio());
	}

	@Override
	public double calcolaPrezzoFinale() {
		double prezzoFinale;
		prezzoFinale= getPrezzoBase()+5.00;
		if(postiLimitati) prezzoFinale+=3.00;
		if(isSerale()) prezzoFinale+=1.00;
		return prezzoFinale;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public String getOspite() {
		return ospite;
	}

	public void setOspite(String ospite) {
		this.ospite = ospite;
	}

	public boolean isPostiLimitati() {
		return postiLimitati;
	}

	public void setPostiLimitati(boolean postiLimitati) {
		this.postiLimitati = postiLimitati;
	}
	
	@Override
	public String getTipoProiezione() {
		return "Evento speciale";
	}


	
//TO STRING
	@Override
	public String toString() {
		return getTipoProiezione() + "\n" + super.toString() + 	", nome evento: " + nomeEvento +
																", ospite: " + ospite +
																", posti: " + ((postiLimitati) ? "limitati" : "illimitati")
																+ "] ";
	}
}
