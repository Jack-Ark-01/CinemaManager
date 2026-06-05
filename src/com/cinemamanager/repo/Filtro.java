package com.cinemamanager.repo;

public interface Filtro<T> {
	
	boolean accetta(T elemento);
	
}
