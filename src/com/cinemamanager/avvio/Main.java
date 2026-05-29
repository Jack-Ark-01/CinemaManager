package com.cinemamanager.avvio;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import com.cinemamanager.models.*;
import com.cinemamanager.utility.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		
	//ALL LISTS
		List<Film> listaFilm = Factory.creaFilm("C:\\Users\\arche\\Documents\\Corso Generation - Java Developer\\Sessioni tecniche\\Progetti\\EclipseWorkspaces\\Root\\CinemaManager\\fileditesto\\filmTest.txt");
		
		List<Proiezione> listaProiezioni = Factory.creaProiezione("C:\\Users\\arche\\Documents\\Corso Generation - Java Developer\\Sessioni tecniche\\Progetti\\EclipseWorkspaces\\Root\\CinemaManager\\fileditesto\\proiezioniTest.txt");
		
		
		
		
		
	//REPORT FILM
		System.out.println("*** REPORT FILM ***");
		
		for(Film film : listaFilm) {
			System.out.println(film);
		}
		
		System.out.println("----------------------------------------");
		
		
		
		
		
	//REPORT SALA
		System.out.println("*** REPORT SALE ***");
		
//		for(Film film : listaFilm) {
//			System.out.println(film);
//		}
		
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
		
		

				
				
				
				
				
				
				
				
			
				
				
				
				
				
				
				
				
	}
}
