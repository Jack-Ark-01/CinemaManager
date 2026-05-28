package com.cinemamanager.aggregatore;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.cinemamanager.models.Archivio;
import com.cinemamanager.models.Film;
import com.cinemamanager.models.Proiezione;
import com.cinemamanager.models.Sala;

public class LettoreDatiCinema {

	
	public List<Sala>  leggiFileSale() throws Exception{
		
			
		try {
			File file = new File("/Users/tiatenco/Desktop/GestioneCinema/CinemaManager/come.cinemamanager.fileditesto/saleTest.txt");
			Scanner fileScanner = new Scanner(file);
			Map<Integer, Sala> mappa = new HashMap<Integer, Sala>();
			while(fileScanner.hasNextLine()) {
				
				String[] riga= fileScanner.nextLine().split(",");
				mappa.put(Integer.parseInt(riga[0]),new Sala());
			}
		} catch (Exception e) {
			
			System.out.println("errore nella lettura del file ");
		}

	
	
	}
	
	
}
