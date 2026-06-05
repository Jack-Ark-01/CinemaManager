package com.cinemamanager.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import com.cinemamanager.repo.Identificabile;
import com.cinemamanager.utility.ValidatoreCinema;

public class Film implements Identificabile {

	
//PROPRIETA'
	private int id; 
	private String titolo; 
	private String regista; 
	private int durataMinuti; 
	private LocalDate dataUscita;
	private Set<GenereFilm> generi;
	
	
//COSTRUTTORE
	public Film()
	{
		
	}


	public Film(int id, String titolo, String regista, int durataMinuti, LocalDate dataUscita, Set<GenereFilm> generi) {
		super();
		ValidatoreCinema.validaFilm(new Film(id, titolo, regista, durataMinuti, dataUscita, generi));
	}

	
	
	
//GETTERS E SETTERS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}


	public String getRegista() {
		return regista;
	}


	public void setRegista(String regista) {
		this.regista = regista;
	}


	public int getDurataMinuti() {
		return durataMinuti;
	}


	public void setDurataMinuti(int durataMinuti) {
		this.durataMinuti = durataMinuti;
	}


	public LocalDate getDataUscita() {
		return dataUscita;
	}


	public void setDataUscita(LocalDate dataUscita) {
		this.dataUscita = dataUscita;
	}


	public Set<GenereFilm> getGeneri() {
		return generi;
	}


	public void setGeneri(Set<GenereFilm> generi) {
		this.generi = generi;
	}


	
	
	
	
//METODI
	
	//TO STRING
	@Override
	public String toString() {
		return "Film [id: " + id +
				", titolo: " + titolo +
				", regista: " + regista +
				", durataMinuti: " + durataMinuti
				+ ", dataUscita: " + dataUscita +
				", generi: " + generi + "] ";
	}
	
	
	void aggiungiGenere(GenereFilm genere) {
		if(this.generi.add(genere)) {
			System.out.println("Genere aggiunto");
			return;
		}
		System.out.println("Errore genere non aggiunto");
	}
	
	void rimuoviGenere(GenereFilm genere) {
		if(this.generi.remove(genere)) {
			System.out.println("Genere rimosso");
			return;
		}
		System.out.println("Genere non presente");
	}
	
	boolean contieneGenere(String genere) {
		
		for(GenereFilm g: this.generi) {
			if(String.valueOf(g).equalsIgnoreCase(genere)) {
				System.out.println("Genere contenuto");
				return true;
			}
		}
		System.out.println("Genere non contenuto");
		return false;
		
//		Prima versione(non corretta)
//		if(this.generi.contains(genere)) {
//			System.out.println("Genere contenuto");
//			return true;
//		}
//		System.out.println("Genere non contenuto");
//		return false;
	}
	
	boolean isFilmRecente() {
		LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
		return this.dataUscita.isAfter(twoYearsAgo);
	}
	
	int getAnniDaUscita() {
		return Period.between(this.dataUscita, LocalDate.now()).getYears();
	}
	
}
