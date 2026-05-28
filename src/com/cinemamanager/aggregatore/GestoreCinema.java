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
	
}
