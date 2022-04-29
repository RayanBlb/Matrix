package matrix;

import java.util.*;

public class Hero {
	private String nom;
	private List<Ressource> ble;
	private List<Ressource> bois;
	private Pierre pierre;
	private ObjetManufacture farine;
	private ObjetManufacture feu;
	private int poids;
	private int nbPartie;
	private int[] position;
	
	public Hero(String nom) {
		this.nom = nom;
		
		this.ble = new ArrayList<Ressource>();
		this.bois = new ArrayList<Ressource>();
		
		this.pierre = null;
		this.farine = null;
		this.feu = null;
		
		this.poids = 0;
		this.nbPartie = 0;
		
		this.position = new int[2];
		position[0] = 0;
		position[1] = 0;
	}
	
	public void seDeplacer(Direction direction) {
		
	}
	
	public void prendre() {
		
	}
	
	public void jeter(Ressource r) {
		
	}
	
	public boolean fairePain() {
		boolean resultat = true;
		
		return resultat;
	}
	
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<Ressource> getBle() {
		return ble;
	}
	public void setBle(List<Ressource> ble) {
		this.ble = ble;
	}
	public List<Ressource> getBois() {
		return bois;
	}
	public void setBois(List<Ressource> bois) {
		this.bois = bois;
	}
	public Pierre getPierre() {
		return pierre;
	}
	public void setPierre(Pierre pierre) {
		this.pierre = pierre;
	}
	public ObjetManufacture getFarine() {
		return farine;
	}
	public void setFarine(ObjetManufacture farine) {
		this.farine = farine;
	}
	public ObjetManufacture getFeu() {
		return feu;
	}
	public void setFeu(ObjetManufacture feu) {
		this.feu = feu;
	}
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}
	public int[] getPosition() {
		return position;
	}
	public void setPosition(int[] position) {
		this.position = position;
	}
	public int getNbPartie() {
		return nbPartie;
	}
	public void setNbPartie(int nbPartie) {
		this.nbPartie = nbPartie;
	}
}
