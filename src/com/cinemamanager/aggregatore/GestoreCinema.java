package com.cinemamanager.aggregatore;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cinemamanager.models.*;
import com.cinemamanager.repo.Filtro;

public class GestoreCinema {
	
//SINGLETON
	private static final GestoreCinema GESTORE = 
			new GestoreCinema();
	
	
//PROPRIETA'
	private Archivio<Film> archivioFilm;
	private Archivio<Sala> archivioSale;
	private Archivio<Proiezione> archivioProiezioni;
	private List<Proiezione> programmazione;
	private Map<LocalDate, List<Proiezione>> proiezioniPerData;
	private Map<Integer, List<Proiezione>> proiezioniPerSala;
	private Map<String, List<Film>> filmPerGenere;
	
	
//COSTRUTTORE
	private GestoreCinema() {
		this.archivioFilm = new Archivio<>();
		this.archivioSale = new Archivio<>();
		this.archivioProiezioni = new Archivio<>();
		this.programmazione = new ArrayList<>();
		this.proiezioniPerData = new HashMap<>();
		this.proiezioniPerSala = new HashMap<>();
		this.filmPerGenere = new HashMap<>();
	}
	
	
//GETTERS E SETTERS
	public static GestoreCinema getGestore() {
		return GESTORE;
	}
	
	public Archivio<Film> getArchivioFilm() {
		return archivioFilm;
	}



	public void setArchivioFilm(Archivio<Film> archivioFilm) {
		this.archivioFilm = archivioFilm;
	}



	public Archivio<Sala> getArchivioSale() {
		return archivioSale;
	}



	public void setArchivioSale(Archivio<Sala> archivioSale) {
		this.archivioSale = archivioSale;
	}



	public Archivio<Proiezione> getArchivioProiezioni() {
		return archivioProiezioni;
	}



	public void setArchivioProiezioni(Archivio<Proiezione> archivioProiezioni) {
		this.archivioProiezioni = archivioProiezioni;
	}



	public List<Proiezione> getProgrammazione() {
		return programmazione;
	}



	public void setProgrammazione(List<Proiezione> programmazione) {
		this.programmazione = programmazione;
	}



	public Map<LocalDate, List<Proiezione>> getProiezioniPerData() {
		return proiezioniPerData;
	}



	public void setProiezioniPerData(Map<LocalDate, List<Proiezione>> proiezioniPerData) {
		this.proiezioniPerData = proiezioniPerData;
	}



	public Map<Integer, List<Proiezione>> getProiezioniPerSala() {
		return proiezioniPerSala;
	}



	public void setProiezioniPerSala(Map<Integer, List<Proiezione>> proiezioniPerSala) {
		this.proiezioniPerSala = proiezioniPerSala;
	}



	public Map<String, List<Film>> getFilmPerGenere() {
		return filmPerGenere;
	}



	public void setFilmPerGenere(Map<String, List<Film>> filmPerGenere) {
		this.filmPerGenere = filmPerGenere;
	}


//METODI
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
	
	
	public List<Proiezione> filtraProiezione(Filtro<Proiezione> filtro)
	{
		List<Proiezione> listaFiltrata = new ArrayList<>();
		for(Proiezione p : programmazione)
		{
			if(filtro.accetta(p))
			{
				listaFiltrata.add(p);
			}
			
		}
		
		return listaFiltrata;
	}
	
	
	public List<Film> filtraFilm(Filtro<Film> filtro)
	{
		List<Film> listaFiltrata = new ArrayList<>();
		
		for(Film f : archivioFilm.trovaTutti())
		{
			if(filtro.accetta(f))
			{
				listaFiltrata.add(f);
			}
		}
		return listaFiltrata;
	}
	
	
	
	public void stampaDettaglioSpecifico(Proiezione proiezione) {
		
		if(proiezione instanceof ProiezioneStandard) {

			System.out.println(					"Titolo: " 			+	proiezione.getFilm().getTitolo()
								+	"\n"		+	"Sala: "				+	proiezione.getSala().getNome()
								+	"\n"		+	"Data: "				+	proiezione.getData().toString()
								+	"\n"		+	"Ora: "				+	proiezione.getOraInizio().toString()
								+	"\n"		+	"Prezzo finale: "	+	((ProiezioneStandard) proiezione).calcolaPrezzoFinale()	+	"€"
					);
		}
		else if(proiezione instanceof Proiezione3D) {
			
			if(proiezione.getSala() == null) {
				return;
			}

			System.out.println(					"Titolo: " 			+	proiezione.getFilm().getTitolo()
								+	"\n"		+	"Sala: "				+	proiezione.getSala().getNome()
								+	"\n"		+	"Supplemento per "
											+	"occhiali 3D: "		+	((Proiezione3D) proiezione).getSupplemento3D()
								+	"\n"		+	"Occhiali "			+	(((Proiezione3D) proiezione).isOcchialiInclusi() ? "inclusi" : "non inclusi")
								+	"\n"		+	"Prezzo finale: "	+	((Proiezione3D) proiezione).calcolaPrezzoFinale()	+	"€"
								);
		}
		else if(proiezione instanceof EventoSpeciale) {
			
			EventoSpeciale evento = (EventoSpeciale) proiezione;
			
			System.out.println(					"Nome evento: " 		+	evento.getNomeEvento()
								+	"\n"		+	"Ospite: "			+	evento.getOspite()
								+	"\n"		+	"Posti "				+	(evento.isPostiLimitati() ? "limitati" : "illimitati")
								+	"\n"		+	"Prezzo finale: "	+	evento.calcolaPrezzoFinale()	+	"€"
								);
		}
	}
	
	
	public void ordinaPerDataOra(){
		this.programmazione.sort((p1, p2)
				-> p1.getDataOraInizio().compareTo(p2.getDataOraInizio()));
	}
	
	
	
	public void ordinaPerPrezzoFinaleCrescente(){
		this.programmazione.sort((p1, p2)
				-> Double.compare(p1.calcolaPrezzoFinale(), p2.calcolaPrezzoFinale())); 
	}
	
	
	List<Film> ordinaPerDurataDecrescente() {
		List<Film> film = archivioFilm.trovaTutti();
		film.sort((f1, f2) -> Integer.compare(f2.getDurataMinuti(), f1.getDurataMinuti()));
		return film;
	}
	
	
	
}
