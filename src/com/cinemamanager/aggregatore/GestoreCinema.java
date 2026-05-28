package com.cinemamanager.aggregatore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cinemamanager.models.Archivio;
import com.cinemamanager.models.Film;
import com.cinemamanager.models.GenereFilm;
import com.cinemamanager.models.Proiezione;
import com.cinemamanager.models.Sala;
import com.cinemamanager.repo.Filtro;

public class GestoreCinema {
	
	private Archivio<Film> archivioFilm;
	private Archivio<Sala> archivioSale;
	private Archivio<Proiezione> archivioProiezioni;
	private List<Proiezione> programmazione;
	private Map<LocalDate, List<Proiezione>> proiezioniPerData;
	private Map<Integer, List<Proiezione>> proiezioniPerSala;
	private Map<String, List<Film>> filmPerGenere;
	
	public GestoreCinema() {
		this.archivioFilm = new Archivio<>();
		this.archivioSale = new Archivio<>();
		this.archivioProiezioni = new Archivio<>();
		this.programmazione = new ArrayList<>();
		this.proiezioniPerData = new HashMap<>();
		this.proiezioniPerSala = new HashMap<>();
		this.filmPerGenere = new HashMap<>();
	}
	
	public void aggiungiFilm(Film film) {
		if(film == null)
			return;
		archivioFilm.aggiungi(film);
		if(film.getGeneri() != null) {
			for(GenereFilm genereEnum : film.getGeneri()) {
				String genereKey = genereEnum.name();
				filmPerGenere.putIfAbsent(genereKey, new ArrayList<>());
				filmPerGenere.get(genereKey).add(film);
			}
		}
	}
	
	public List<Proiezione> filtraProiezione(Filtro filtro)
	{
		for(Proiezione p : programmazione)
		{
			
		}
		
		
		return null;
	}
	
	public void aggiungereSala(Sala sala) {
		if(sala != null)
			archivioSale.aggiungi(sala);
	}
	
	public void aggiungiProiezione(Proiezione proiezione) {
		if(proiezione == null)
			return;
		
		archivioProiezioni.aggiungi(proiezione);
		programmazione.add(proiezione);
		LocalDate dataProiezione = proiezione.getData();
		proiezioniPerData.putIfAbsent(dataProiezione, new ArrayList<>());
		proiezioniPerData.get(dataProiezione).add(proiezione);
		
		if(proiezione.getSala() != null) {
			int idSala = proiezione.getSala().getId();
			proiezioniPerSala.putIfAbsent(idSala, new ArrayList<>());
			proiezioniPerSala.get(idSala).add(proiezione);
		}
	}
	
	public List<Proiezione> cercaProiezioniPerData(LocalDate data) {
		if(proiezioniPerData.containsKey(data))
			return proiezioniPerData.get(data);
		else
			return new ArrayList<>();
	}
	
	public List<Proiezione> cercaProiezioniPerSala(int idSala) {
		if(proiezioniPerSala.containsKey(idSala))
			return proiezioniPerSala.get(idSala);
		else
			return new ArrayList<>();
	}
	
	public List<Film> cercaFilmGenere(String genere) {
		if(genere == null)
			return new ArrayList<>();
		if(filmPerGenere.containsKey(genere.toUpperCase()))
			return filmPerGenere.get(genere);
		else
			return new ArrayList<>();
	}
	
	public List<Proiezione> cercaProiezioniFutere(){
		List<Proiezione> future = new ArrayList<>();
		for(Proiezione p : programmazione) {
			if(!p.isTerminata())
				future.add(p);
		}
		return future;
	}
	
	public List<Proiezione> cercaProiezioniDiOggi(){
		List<Proiezione> diOggi = new ArrayList<>();
		LocalDate oggi = LocalDate.now();
		for(Proiezione p : programmazione) {
			if(p.getData().equals(oggi))
				diOggi.add(p);
		}
		return diOggi;
	}
	
	public List<Proiezione> cercaProiezioniSerali(){
		List<Proiezione> serali = new ArrayList<>();
		for(Proiezione p : programmazione) {
			if(p.isSerale())
				serali.add(p);
		}
		return serali;
	}
	
}
