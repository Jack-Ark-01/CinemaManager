
package com.cinemamanager.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import com.cinemamanager.repo.Identificabile;
import com.cinemamanager.repo.Prezzabile;
import com.cinemamanager.repo.Programmabile;



public abstract class Proiezione implements Identificabile,Programmabile,Prezzabile{

	private int id;
	private Film film;
	private Sala sala;
	private LocalDate data;
	private LocalTime oraInizio;
	private double prezzoBase;
	private Set<String> tag;
	
	public Proiezione(int id,Sala sala,Film film,LocalDate data ,LocalTime oraInizio,double prezzobase,Set<String> tag) {
	    setId(id);
	    setFilm(film);
	    setSala(sala);
	    setData(data);
	    setOraInizio(oraInizio);
	    
	    if (tag != null) {

	        setTag(tag);

	    } else {

	    	this.tag= new HashSet<String>();

	    }
	}
	
	//COSTRUTTORE PER LETTURA FILE
	public Proiezione(int idProiezione, int idFilm, int idSala, LocalDate data, LocalTime oraInizio, double prezzobase, Set<String> tag) {
		setId(idProiezione);
		this.film.setId(idFilm);
		this.sala.setId(idSala);
		setData(data);
	    setOraInizio(oraInizio);
	    
	    if (tag != null) {

	        setTag(tag);

	    } else {

	    	this.tag= new HashSet<String>();

	    }
	   
	}
	
	public void aggiungiTag(String tag) {
		this.tag.add(tag);
		
	}
	
	public boolean contieneTag(String tag) {
		
		return this.tag.contains(tag);
	}

	public boolean isOggi() {
		LocalDate oggi = LocalDate.now();
		return oggi.equals(data);
	}
	public boolean isNelWeekEnd() {
		return data.getDayOfWeek().equals(DayOfWeek.SATURDAY )||data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
	} 
	
	public boolean isSerale() {

	    LocalTime sera = LocalTime.of(19, 30);
	    return oraInizio.isAfter(sera);

	}
	
	public boolean isTerminata() {
		
	    LocalDateTime inizio = LocalDateTime.of(data, oraInizio);
	    return inizio.isBefore(LocalDateTime.now());
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}



	public LocalTime getOraInizio() {
		return oraInizio;
	}

	public void setOraInizio(LocalTime oraInizio) {
		this.oraInizio = oraInizio;
	}

	public double getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(double prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public Set<String> getTag() {
		return tag;
	}

	public void setTag(Set<String> tag) {
		this.tag = tag;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String getDettagliBase() {
		
		return "id : "+id+" sala : "+sala+" film : "+film;
		
	}
	public String getTipoProiezione() {
		return "proiezione standard";
	}
	
	
	
	
}
