package com.cinemamanager.models;

public enum GenereFilm {

	AZIONE,
	DRAMMATICO,
	THRILLER,
	FANTASCIENZA, 
	ANIMAZIONE,
	COMMEDIA,
	HORROR;
	
	public static GenereFilm parse(String genere) {
		switch(genere.toUpperCase()) {
			case "AZIONE":
				return AZIONE;
			case "DRAMMATICO":
				return DRAMMATICO;
			case "THRILLER":
				return THRILLER;
			case "FANTASCIENZA":
				return FANTASCIENZA;
			case "ANIMAZIONE":
				return ANIMAZIONE;
			case "COMMEDIA":
				return COMMEDIA;
			case "HORROR":
				return HORROR;
			default:
				return null;
		}
	}
}
