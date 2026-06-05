package com.cinemamanager.utility;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.cinemamanager.models.Film;
import com.cinemamanager.models.GenereFilm;
import com.cinemamanager.models.Sala;

public class ValidatoreCinema {
	private static final Set<GenereFilm> SET_GENERI_DEFAULT = new HashSet<>();
	private static final Film FILM_DEFAULT = new Film(-1, "Sconosciuto", "Sconosciuto", -1, LocalDate.of(2001, 11, 02), SET_GENERI_DEFAULT);
	private static final Set<String> SET_CARATTERISTICHE_DEFAULT = new HashSet<>();
	private static final Sala SALA_DEFAULT = new Sala(-1, "Sconosciuto", -1, false);

	public static Film validaFilm(Film film) {
		
		Film filmValidato = new Film();
		
	//TITOLO
		if(film.getTitolo().isBlank() || film.getTitolo().isEmpty() || film.getTitolo() == null) {
			filmValidato.setTitolo(FILM_DEFAULT.getTitolo());
		}
		else {
			filmValidato.setTitolo(film.getTitolo());
		}
	//REGISTA
		if(film.getRegista().isBlank() || film.getRegista().isEmpty() || film.getRegista() == null) {
			filmValidato.setRegista(FILM_DEFAULT.getRegista());
		}
		else {
			filmValidato.setRegista(film.getRegista());
		}
	//DURATA
		if(film.getDurataMinuti() <= 0) {
			filmValidato.setDurataMinuti(FILM_DEFAULT.getDurataMinuti());
		}
		else {
			filmValidato.setDurataMinuti(film.getDurataMinuti());
		}
	//DATA DI USCITA
		if(film.getDataUscita() == null) {
			filmValidato.setDataUscita(FILM_DEFAULT.getDataUscita());
		}
		else {
			filmValidato.setDataUscita(film.getDataUscita());
		}
	//GENERI
		if(film.getGeneri().isEmpty()) {
			filmValidato.setGeneri(SET_GENERI_DEFAULT);
		}
		else {
			filmValidato.setGeneri(film.getGeneri());
		}
		
		return filmValidato;
	}
	
public static Sala validaSala(Sala sala) {
		
		Sala salaValidata = new Sala();
		
	//NOME
		if(sala.getNome().isBlank() || sala.getNome().isEmpty() || sala.getNome() == null) {
			salaValidata.setNome(SALA_DEFAULT.getNome());
		}
		else {
			salaValidata.setNome(sala.getNome());
		}
	//NUMERO POSTI
		if(sala.getNumeroPosti() > 0) {
			salaValidata.setNumeroPosti(SALA_DEFAULT.getNumeroPosti());;
		}
		else {
			salaValidata.setNumeroPosti(sala.getNumeroPosti());
		}
	//CARATTERISTICHE
		if(sala.getCaratteristiche().isEmpty()) {
			salaValidata.setCaratteristiche(SET_CARATTERISTICHE_DEFAULT);
		}
		else {
			salaValidata.setCaratteristiche(sala.getCaratteristiche());
		}
		
		return salaValidata;
	}
}
