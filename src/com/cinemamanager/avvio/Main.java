package com.cinemamanager.avvio;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.cinemamanager.aggregatore.GestoreCinema;
import com.cinemamanager.models.*;
import com.cinemamanager.repo.Notificatore;
import com.cinemamanager.utility.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		
	//ALL LISTS
		GestoreCinema gestore = GestoreCinema.getGestore();
		
		List<Film> listaFilm = Factory.creaFilm("C:\\Users\\arche\\Documents\\Corso Generation - Java Developer\\Sessioni tecniche\\Progetti\\EclipseWorkspaces\\Root\\CinemaManager\\fileditesto\\filmTest.txt");
		List<Sala> listaSale = Factory.creaSala("C:\\Users\\arche\\Documents\\Corso Generation - Java Developer\\Sessioni tecniche\\Progetti\\EclipseWorkspaces\\Root\\CinemaManager\\fileditesto\\saleTest.txt");
		List<Proiezione> listaProiezioni = Factory.creaProiezione("C:\\Users\\arche\\Documents\\Corso Generation - Java Developer\\Sessioni tecniche\\Progetti\\EclipseWorkspaces\\Root\\CinemaManager\\fileditesto\\proiezioniTest.txt");
		
		for(Proiezione p : listaProiezioni) {
			for(Sala s : listaSale) {
				
				if(p.getSala() == null) {
					continue;
				}
				
				if(p.getSala().getId() == s.getId()) {
					p.setSala(s);
				}
			}
		}
		
		for(Proiezione p : listaProiezioni) {
			for(Film f : listaFilm) {
				
				if(p.getFilm() == null) {
					continue;
				}
				
				if(p.getFilm().getId() == f.getId()) {
					p.setFilm(f);
				}
			}
		}
		
		
		
	//REPORT FILM
		System.out.println("*** REPORT FILM ***");
		
		for(Film film : listaFilm) {
			System.out.println(film);
		}
		
		System.out.println("----------------------------------------");
		
		
		
		
		
	//REPORT SALA
		System.out.println("*** REPORT SALE ***");
		
		for(Sala sala : listaSale) {
			System.out.println(sala);
		}
		
		System.out.println("----------------------------------------");
		
		
		
		
		
	//REPORT PROIEZIONI
		System.out.println("*** REPORT PROIEZIONI ***");
		
		for(Proiezione proiezione : listaProiezioni) {
			
			System.out.println(proiezione);
		}
		
		System.out.println("----------------------------------------");
		
		
		
		
		
	//REPORT PROIEZIONI DI OGGI
		System.out.println("*** REPORT PROIEZIONI DI OGGI ***");
								
		for(Proiezione proiezione : listaProiezioni) {
									
			if(proiezione.isOggi()) {
			System.out.println(proiezione);
			}
		}
								
		System.out.println("----------------------------------------");
		
				
		
		
	//REPORT PROIEZIONI FUTURE
		System.out.println("*** REPORT PROIEZIONI FUTURE ***");
				
		for(Proiezione proiezione : listaProiezioni) {
					
			if(proiezione.getData().isAfter(LocalDate.now())) {
			System.out.println(proiezione);
			}
		}
				
		System.out.println("----------------------------------------");
				
		
		
		
		
		
	//REPORT PROIEZIONI DI UNA DETERMINATA DATA
		LocalDate dataTest1 = LocalDate.of(2026,6,8);
				
		System.out.println("*** REPORT PROIEZIONI DI UNA DETERMINATA DATA ***");
						
		for(Proiezione proiezione : listaProiezioni) {
							
			if((proiezione.getData()).equals(dataTest1)) {
			System.out.println(proiezione);
			}
		}
						
		System.out.println("----------------------------------------");
		
		
		
		
		
		
	//REPORT PROIEZIONI PER SALA
		Sala salaReport6 = listaSale.get(0);
		System.out.println("*** REPORT PROIEZIONI PER " + salaReport6.getNome().toUpperCase() + " ***");
								
		for(Proiezione proiezione : listaProiezioni) {
			
			if(proiezione.getSala() == null) {
				continue;
			}
			if(Integer.compare(proiezione.getSala().getId(), salaReport6.getId()) == 0) {
				System.out.println(proiezione);
			}
		}
								
		System.out.println("----------------------------------------");
		
		
		
		
		
	//REPORT FILM PER GENERE
		System.out.println("*** REPORT FILM PER GENERE " + GenereFilm.FANTASCIENZA + " ***");
								
		for(Film film : listaFilm) {
			
			Set<GenereFilm> setGeneri = film.getGeneri();
			
			for(GenereFilm g : setGeneri) {
				if(g.equals(GenereFilm.FANTASCIENZA)) {
					System.out.println(film);
				}
			}
		}
								
		System.out.println("----------------------------------------");
		
	
		
		
		
	//REPORT PROIEZIONI SERALI
		System.out.println("*** REPORT PROIEZIONI SERALI ***");
										
		for(Proiezione p : listaProiezioni) {
			
			if(p.isSerale()) {
				System.out.println(p);
			}
		}
										
		System.out.println("----------------------------------------");
		
		
		
		
		
	//REPORT PROIEZIONI COSTOSE (prezzo > 15)
		System.out.println("*** REPORT PROIEZIONI COSTOSE ***");

		gestore.setProgrammazione(listaProiezioni);
		
		List<Proiezione> proiezioniCostose = gestore.filtraProiezione(p -> p.calcolaPrezzoFinale() > 15);
		
		for(Proiezione p : proiezioniCostose) {
				System.out.println(p);
		}
												
		System.out.println("----------------------------------------");
		
		
		
		
		
		
	//REPORT PROIEZIONI COSTOSE (prezzo > 15)
		System.out.println("*** REPORT DETTAGLIO SPECIFICO CON INSTANCEOF ***");
		
		for(Proiezione p : gestore.getProgrammazione()) {
			gestore.stampaDettaglioSpecifico(p);
		}
														
		System.out.println("----------------------------------------");
		
	//LAMBDA
		
		//ORDINAMENTO PROIEZIONI DATA E ORA
			Collections.sort(listaProiezioni, (p, v) -> p.getDataOraInizio().compareTo(v.getDataOraInizio()));
			
			System.out.println("*** REPORT ORDINAMENTO PROIEZIONI PER DATA E ORA ***");
			
			for(Proiezione p : listaProiezioni) {
				System.out.println(p.getTipoProiezione() + ", Data e ora: " + p.getDataOraInizio());
			}
			
			System.out.println("----------------------------------------");
			
		
			
		//ORDINAMENTO PROIEZIONI PER PREZZO CRESCENTE
			Collections.sort(listaProiezioni, (p, v) -> Double.compare(p.calcolaPrezzoFinale(), v.calcolaPrezzoFinale()));
			System.out.println("*** REPORT ORDINAMENTO PROIEZIONI PER PREZZO CRESCENTE ***");
					
			for(Proiezione p : listaProiezioni) {
				System.out.println(p.getTipoProiezione() + " - Prezzo: " + p.calcolaPrezzoFinale() + "€");
			}
					
			System.out.println("----------------------------------------");
			
			
			
		//ORDINAMENTO FILM PER DURATA IN MINUTI CRESCENTE
			Collections.sort(listaFilm, (p, v) -> Integer.compare(p.getDurataMinuti(), v.getDurataMinuti()));
			System.out.println("*** REPORT ORDINAMENTO FILM PER DURATA IN MINUTI CRESCENTE ***");
					
			for(Film f : listaFilm) {
				System.out.println(f.getTitolo() + " -  Prezzo: " + f.getDurataMinuti() + "€");
			}
					
			System.out.println("----------------------------------------");
			
			
			
			
			
		//NOTIFICA CON CLASSE ANONIMA (punto 17)
			Notificatore notifica = new Notificatore(){

				@Override
				public void inviaNotifica(Proiezione proiezione) {
					System.out.println(proiezione.getTipoProiezione() + ", Data e ora: " + proiezione.getDataOraInizio());
				}
					
			};
			
			notifica.inviaNotifica(listaProiezioni.get(0));
	}
}
