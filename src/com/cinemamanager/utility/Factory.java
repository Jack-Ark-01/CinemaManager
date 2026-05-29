package com.cinemamanager.utility;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cinemamanager.models.EventoSpeciale;
import com.cinemamanager.models.Film;
import com.cinemamanager.models.GenereFilm;
import com.cinemamanager.models.Proiezione;
import com.cinemamanager.models.Proiezione3D;
import com.cinemamanager.models.ProiezioneStandard;

public class Factory {
	
   public static List<Film> creaFilm(String percorso) throws FileNotFoundException {
	
		List<String[]> lista = LettoreDatiCinema.leggiFile(percorso);
		
		List<Film> listaFilm = new ArrayList<>();
		
		for(String[] riga : lista) {
			
			Film filmTemp = new Film();
			
			filmTemp.setId(Integer.parseInt(riga[0]));
			filmTemp.setTitolo(riga[1]);
			filmTemp.setRegista(riga[2]);
			filmTemp.setDurataMinuti(Integer.parseInt(riga[3]));
			filmTemp.setDataUscita(LocalDate.parse(riga[4]));
			
			String[] vettoreEnumGeneri = riga[5].split(",");
			
			Set<GenereFilm> generi = new HashSet<>();
			
			for(String genere : vettoreEnumGeneri) {
				if(GenereFilm.parse(genere) != null) {		
					generi.add(GenereFilm.parse(genere));
				}
			}
			
			filmTemp.setGeneri(generi);
			
			listaFilm.add(filmTemp);
		}
		
		return listaFilm;	
	}
   
   public static List<Proiezione> creaProiezione(String percorso) throws FileNotFoundException{
	   
	   List<String[]> lista = LettoreDatiCinema.leggiFile(percorso);
		
		List<Proiezione> listaProiezioni = new ArrayList<>();
		
//		private int id;
//		private Film film;
//		private Sala sala;
//		private LocalDate data;
//		private LocalTime oraInizio;
//		private double prezzoBase;
//		private Set<String> tag;
		
		for(String[] riga : lista) {
			switch(riga[1].toUpperCase()) {
				case "STANDARD":
					Set<String> s = new HashSet<>();
					
					for(String valore : riga[10].split(",")) {
						s.add(valore);
					}
					
					ProiezioneStandard p = new ProiezioneStandard(	Integer.parseInt(riga[0]),
																	Integer.parseInt(riga[2]),
																	Integer.parseInt(riga[3]),
																	LocalDate.parse(riga[4]),
																	LocalTime.parse(riga[5]),
																	Double.parseDouble(riga[6]), //riga 7, 8, 9 non sono attributi di proiezione standard
																	s							
																	);
					
					listaProiezioni.add(p);
					break;
					
				case "TRE_D":
					Set<String> s1 = new HashSet<>();
					
					for(String valore : riga[10].split(",")) {
						s1.add(valore);
					}
					
					Proiezione3D p3D = new Proiezione3D(			Integer.parseInt(riga[0]),
																	Integer.parseInt(riga[2]),
																	Integer.parseInt(riga[3]),
																	LocalDate.parse(riga[4]),
																	LocalTime.parse(riga[5]),
																	Double.parseDouble(riga[6]),
																	Double.parseDouble(riga[7]), 
																	Boolean.parseBoolean(riga[8]), //riga 9 non è un attributo di proiezione 3D
																	s1							
																	);
					
					listaProiezioni.add(p3D);
					break;
					
					
				case "EVENTO":
					Set<String> s2 = new HashSet<>();
					
					for(String valore : riga[10].split(",")) {
						s2.add(valore);
					}
					
					EventoSpeciale es = new EventoSpeciale(			Integer.parseInt(riga[0]),
																	Integer.parseInt(riga[2]),
																	Integer.parseInt(riga[3]),
																	LocalDate.parse(riga[4]),
																	LocalTime.parse(riga[5]),
																	Double.parseDouble(riga[6]),
																	riga[7],
																	riga[8],
																	Boolean.parseBoolean(riga[9]),
																	s2							
																	);
					
					listaProiezioni.add(es);
					break;
			}
		}
		
		return listaProiezioni;	
   }


}
