package com.cinemamanager.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
	} //legge file
}
