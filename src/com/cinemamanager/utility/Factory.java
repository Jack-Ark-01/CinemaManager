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
import com.cinemamanager.models.Sala;

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

		
		for(String[] riga : lista) {
			switch(riga[1].toUpperCase()) {
				case "STANDARD":
					Set<String> s = new HashSet<>();
					
					for(String valore : riga[10].split(",")) {
						s.add(valore);
					}
					
					Film film = new Film();
					
					film.setId(Integer.parseInt(riga[2]));
					
					Sala sala = new Sala();
					
					sala.setId(Integer.parseInt(riga[3]));
					
					ProiezioneStandard p = new ProiezioneStandard(	Integer.parseInt(riga[0]),
																	film,
																	sala,
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
					
					Film film1 = new Film();
					
					film1.setId(Integer.parseInt(riga[2]));
					
					Sala sala1 = new Sala();
					
					sala1.setId(Integer.parseInt(riga[3]));
					
					Proiezione3D p3D = new Proiezione3D(				Integer.parseInt(riga[0]),
																	film1,
																	sala1,
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

					Film film2 = new Film();
					
					film2.setId(Integer.parseInt(riga[2]));
					
					Sala sala2 = new Sala();
					
					sala2.setId(Integer.parseInt(riga[3]));
					
					EventoSpeciale es = new EventoSpeciale(			Integer.parseInt(riga[0]),
																	film2,
																	sala2,
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
