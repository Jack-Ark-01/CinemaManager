package com.cinemamanager.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cinemamanager.repo.Identificabile;

public class Archivio<T extends Identificabile> {
	
	//proprietà??
	private Map<Integer, T> elementi;
	
	//costruttore
	public Archivio()
	{
		this.elementi = new HashMap<>();
	}
	
	public Archivio(Map<Integer, T> elementi)
	{
		this.elementi = elementi;
	}
	
	
	//getters e setters
	public Map<Integer, T> getElementi() {
		return elementi;
	}
	
	public void setElementi(Map<Integer, T> elementi) {
		this.elementi = elementi;
	}
	
	
	//metodi
	public void aggiungi(T elemento)
	{
		elementi.put(elemento.getId(), elemento);
	}
	
	
	public T cercaPerId(int id)
	{
		return elementi.get(id);
	}
	
	
	public boolean rimuoviPerId(int id)
	{
		if(elementi.containsKey(id))
		{
			elementi.remove(id);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public List<T> trovaTutti()
	{
		return new ArrayList<>(elementi.values());
	}
	
	
	
	public int contaElementi()
	{
		return elementi.size();
		
	}
	
	
	
}
