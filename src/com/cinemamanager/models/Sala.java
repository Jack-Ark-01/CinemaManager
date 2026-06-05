package com.cinemamanager.models;

import java.util.HashSet;
import java.util.Set;

import com.cinemamanager.repo.Identificabile;

public class Sala implements Identificabile{
	
	private int id;
	private String nome;
	private int numeroPosti;
	private boolean supporta3D;
	private Set<String> caratteristiche;
	
	public Sala(int id, String nome, int numeroPosti, boolean supporta3D) {
		setId(id);
		setNome(nome);
		setNumeroPosti(numeroPosti);
		setSupporta3D(supporta3D);
		this.caratteristiche = new HashSet<>();
	}
	
	public Sala() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome != null && !nome.isBlank())
			this.nome = nome;
		else
			this.nome = "Sconosciuto";
	}

	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		if(numeroPosti > 0)
			this.numeroPosti = numeroPosti;
		else
			this.numeroPosti = 1;
	}

	public boolean isSupporta3D() {
		return supporta3D;
	}

	public void setSupporta3D(boolean supporta3d) {
		supporta3D = supporta3d;
	}

	public Set<String> getCaratteristiche() {
		return caratteristiche;
	}

	public void setCaratteristiche(Set<String> caratteristiche) {
		this.caratteristiche = caratteristiche;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", nome=" + nome + ", numeroPosti=" + numeroPosti + ", supporta3D=" + supporta3D
				+ ", caratteristiche=" + caratteristiche + "]";
	}
	
	public void aggiungiCaratteristica(String caratteristica) {
		if(caratteristica != null && !caratteristica.isBlank() && !haCaratteristica(caratteristica))
			this.caratteristiche.add(caratteristica);
		else
			System.out.println("Errore, la caratteristica non può essere vuota");
	}
	
	public boolean haCaratteristica(String caratteristica) {
		return this.caratteristiche.contains(caratteristica);
	}
	
	public String getDescrizione() {
		return "Sala ID: " + id +
				"\nNome: " + nome +
				"\nPosti: " + numeroPosti + 
				"\nSupporto 3D: " + supporta3D +
				"\nCaratteristiche: " + caratteristiche;			
	}
}
