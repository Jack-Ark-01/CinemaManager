package com.cinemamanager.utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cinemamanager.aggregatore.GestoreCinema;
import com.cinemamanager.models.Sala;

public class LettoreDatiCinema {

//	● leggere il file delle sale; 
//	● leggere il file delle proiezioni; 
//	● creare gli oggetti Java corrispondenti; 
//	● aggiungerli al GestoreCinema; 
//	● ignorare le righe non valide; 
//	● stampare un messaggio quando una riga non può essere caricata. 
	
	public void caricaFilm(String percorsoFile, GestoreCinema gestore) { 
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
