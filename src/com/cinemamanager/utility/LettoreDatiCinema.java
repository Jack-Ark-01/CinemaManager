package com.cinemamanager.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.cinemamanager.aggregatore.GestoreCinema;
import com.cinemamanager.models.Film;
import com.cinemamanager.models.GenereFilm;
import com.cinemamanager.models.Sala;

public class LettoreDatiCinema {

//	● leggere il file delle sale; 
//	● leggere il file delle proiezioni; 
//	● creare gli oggetti Java corrispondenti; 
//	● aggiungerli al GestoreCinema; 
//	● ignorare le righe non valide; 
//	● stampare un messaggio quando una riga non può essere caricata. 
	
	public static List<String[]> leggiFile(String percorso) throws FileNotFoundException { 
		   File file = new File(percorso);
		   Scanner f = new Scanner(file);
		   
		   List<String[]> lista = new ArrayList<>();
		   
		   while(f.hasNextLine()) {
			   String riga = f.nextLine();
			   String[] valori = riga.split(";");
			   lista.add(valori);
		   }
		   f.close();
		   return lista;
	} //legge film.txt 
	
	
	
	public static  Map<Integer,Sala> leggiFileSale() {
		
		
		try {
			File file = new File("/CinemaManager/come.cinemamanager.fileditesto/saleTest.txt");
			Scanner fileScanner = new Scanner(file);
			Map<Integer, Sala> mappa = new HashMap<Integer, Sala>();
			while(fileScanner.hasNextLine()) {
				
				String[] riga= fileScanner.nextLine().split(",");
				mappa.put(Integer.parseInt(riga[0]),new Sala());
			}
			
			return mappa;
		} catch (Exception e) {
			
			System.out.println("errore nella lettura del file ");
			return null;
		}

	
	
	}
	
	
}
