package matrix;

import java.util.*;

import erreur.ErreurInventaireBoisException;
import outils.Pair;

public class Jeu {
	private Hero joueur;
	private Ressource[][] map;

	static List<Pair> boisCoordonnee = new ArrayList<Pair>();

	public Jeu(String hero, Ressource[][] map) {
		joueur = new Hero(hero);
		this.map = map;
	}

	public void jouer() {
		comportementSimpleJoueur();
	}

	public void verificationJoueur() {
		int[] coordonnee = joueur.getPosition();

		try {
			joueur.prendre(map[coordonnee[0]][coordonnee[1]]);
			map[coordonnee[0]][coordonnee[1]] = null;
		} catch (ErreurInventaireBoisException e) {
			Pair coordoBois = new Pair(coordonnee[0], coordonnee[1]);
			boisCoordonnee.add(coordoBois);
		} catch (Exception e) {

		}

		if (joueur.getBle().size() >= 10) {
			joueur.faireFarine();
		} else if (joueur.getBois().size() >= 5) {
			joueur.faireFeu();
		} else if (joueur.getPierre() != null && joueur.getFarine() != null
				&& map[coordonnee[0]][coordonnee[1]] == null) {
			joueur.jeter(joueur.getPierre());
			map[coordonnee[0]][coordonnee[1]] = new Pierre("pierre");
		}
	}

	public void deplacementJoueurCoordonnee(int x, int y) {

		if (joueur.getPosition()[0] > x) {
			while (x != joueur.getPosition()[0]) {
				try {
					joueur.seDeplacer(Direction.HAUT);
					affichageTest();
				} catch (Exception e) {
				}
			}
		} else {
			while (x != joueur.getPosition()[0]) {
				try {
					joueur.seDeplacer(Direction.BAS);
					affichageTest();
				} catch (Exception e) {
				}
			}
		}

		if (joueur.getPosition()[1] > y) {
			while (y != joueur.getPosition()[1]) {
				try {
					joueur.seDeplacer(Direction.GAUCHE);
					affichageTest();
				} catch (Exception e) {
				}
			}
		} else {
			while (y != joueur.getPosition()[1]) {
				try {
					joueur.seDeplacer(Direction.DROITE);
					affichageTest();
				} catch (Exception e) {
				}
			}
		}
		verificationJoueur();
	}

	public void verificationWin() {
		if (joueur.fairePain() == true && joueur.getPosition()[0] == 9 && joueur.getPosition()[1] == 9) {
			System.out.println("Pain fabriqué");
			System.out.println("You win avec : " + joueur.getNbPartie() + " Coups !!!");
		}
	}

	/**
	 * Comportement simple Joueur
	 */
	
	public void comportementSimpleJoueur() {
		comportementPremierePhase();
		comportementDeuxiemePhase();
		comportementFinalPhase();
	}

	public void comportementPremierePhase() {
		for (int y = 0; y <= 9; y++) {

			for (int x = 0; x < 9; x++) {

				verificationJoueur();

				if (y % 2 == 0) {
					try {
						affichageTest();
						joueur.seDeplacer(Direction.DROITE);
					} catch (Exception e) {
					}
				} else {
					try {
						affichageTest();
						joueur.seDeplacer(Direction.GAUCHE);
					} catch (Exception e) {
					}
				}

			}

			verificationJoueur();

			try {
				affichageTest();
				joueur.seDeplacer(Direction.BAS);
			} catch (Exception e) {
			}

		}
	}

	public void comportementDeuxiemePhase() {
		for (int i = 0; i < boisCoordonnee.size(); i++) {
			Pair vTempo = boisCoordonnee.get(i);
			int x = vTempo.getL();
			int y = vTempo.getR();
			deplacementJoueurCoordonnee(x, y);
		}
	}

	public void comportementFinalPhase() {
		deplacementJoueurCoordonnee(9, 9);
		verificationWin();
	}
	
	/**
	 * Fin comportement Simple
	 */
	
	/**
	 * Affichage
	 */
	
	public void affichageTest() {
		int positionXJoueur = joueur.getPosition()[0];
		int positionYJoueur = joueur.getPosition()[1];
		String affichage = "";
		
		for(int y = 0; y <= 9; y++) {
			for(int i = 0; i <= 9; i++) {
				if(map[y][i] instanceof Ble && (positionXJoueur != y || positionYJoueur != i)) {
					affichage = affichage+" B "+" ";
				}else if(map[y][i] instanceof Ble && positionXJoueur == y && positionYJoueur == i){
					affichage = affichage+"[B]"+" ";
				}else if(map[y][i] instanceof Pierre && (positionXJoueur != y || positionYJoueur != i)) {
					affichage = affichage+" P "+" ";
				}else if(map[y][i] instanceof Pierre && positionXJoueur == y && positionYJoueur == i) {
					affichage = affichage+"[P]"+" ";
				}else if(map[y][i] instanceof Bois && (positionXJoueur != y || positionYJoueur != i)) {
					affichage = affichage+" W "+" ";
				}else if(map[y][i] instanceof Bois && positionXJoueur == y && positionYJoueur == i){
					affichage = affichage+"[W]"+" ";
				}else if(positionXJoueur == y && positionYJoueur == i){
					affichage = affichage+"[-]"+" ";
				}else {
					affichage = affichage+" - "+" ";
				}
			}
			System.out.println(affichage);
			affichage = "";
		}
		joueur.debugJoueur();
	}

	public Hero getJoueur() {
		return joueur;
	}

	public void setJoueur(Hero joueur) {
		this.joueur = joueur;
	}

	public Ressource[][] getMap() {
		return map;
	}

}
