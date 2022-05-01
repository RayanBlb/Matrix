package matrix;

import java.util.*;

import erreur.ErreurInventaireBoisException;
import outils.Pair;

public class Jeu {
	private Hero joueur;
	private Ressource[][] map;

	public Hero getJoueur() {
		return joueur;
	}

	static List<Pair> boisCoordonnee = new ArrayList<Pair>();

	public Jeu(String hero, Ressource[][] map) {
		joueur = new Hero(hero);
		this.map = map;
	}

	public void jouer() {
		comportementSimpleJoueur();
	}

	public int verificationJoueur() {
		int[] coordonnee = joueur.getPosition();
		
		if(joueur.getPhase() == 1 && joueur.getFarine() != null && boisCoordonnee.size() >= 5) {
			joueur.setPhase(2);
			return 2;
		}
		
		if(joueur.getPhase() == 2 && joueur.getFarine() == null) {
			joueur.setPhase(3);
			return 3;
		}

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
		}
		if (joueur.getBois().size() >= 5) {
			joueur.faireFeu();
		}
		if (joueur.getPierre() != null && joueur.getFarine() != null && map[coordonnee[0]][coordonnee[1]] == null) {
			joueur.jeter(joueur.getPierre());
			map[coordonnee[0]][coordonnee[1]] = new Pierre("pierre");
		}
		return 0;
	}

	public int deplacementJoueurCoordonnee(int l, int c) {

		if (joueur.getPosition()[0] > l) {
			while (l != joueur.getPosition()[0]) {
				try {
					joueur.seDeplacer(Direction.HAUT);
					affichageJeu();
				} catch (Exception e) {
				}
			}
		} else {
			while (l != joueur.getPosition()[0]) {
				try {
					joueur.seDeplacer(Direction.BAS);
					affichageJeu();
				} catch (Exception e) {
				}
			}
		}

		if (joueur.getPosition()[1] > c) {
			while (c != joueur.getPosition()[1]) {
				try {
					joueur.seDeplacer(Direction.GAUCHE);
					affichageJeu();
				} catch (Exception e) {
				}
			}
		} else {
			while (c != joueur.getPosition()[1]) {
				try {
					joueur.seDeplacer(Direction.DROITE);
					affichageJeu();
				} catch (Exception e) {
				}
			}
		}
		
		if(verificationJoueur() == 3)return 3;
		
		return 0;
	}

	public void verificationWin() {
		if (joueur.fairePain() == true && joueur.getPosition()[0] == 9 && joueur.getPosition()[1] == 9) {
			System.out.println("Pain fabriqué");
			System.out.println("You win avec : " + joueur.getNbPartie() + " Coups !!!");
		}else {
			System.out.println("You loose avec : " + joueur.getNbPartie() + " Coups !!!");
		}
	}

	public void comportementSimpleJoueur() {
		comportementPremierePhase();
		comportementDeuxiemePhase();
		comportementFinalPhase();
	}

	public int comportementPremierePhase() {
		joueur.setPhase(1);
		affichageJeu();
		for (int l = 0; l <= 9; l++) {
			for (int c = 0; c < 9; c++) {
				if(verificationJoueur() == 2)return 0;

				if (l % 2 == 0) {
					try {
						affichageJeu();
						joueur.seDeplacer(Direction.DROITE);
					} catch (Exception e) {
					}
				} else {
					try {
						affichageJeu();
						joueur.seDeplacer(Direction.GAUCHE);
					} catch (Exception e) {
					}
				}

			}

			if(verificationJoueur() == 2)return 0;

			try {
				affichageJeu();
				joueur.seDeplacer(Direction.BAS);
			} catch (Exception e) {
			}

		}
		return 0;
	}

	public int comportementDeuxiemePhase() {
		joueur.setPhase(2);
		for (int i = 0; i < boisCoordonnee.size(); i++) {
			Pair vTempo = boisCoordonnee.get(i);
			int l = vTempo.getL();
			int c = vTempo.getR();
			if(joueur.getFeu() != null)return 0;
			if(deplacementJoueurCoordonnee(l, c) == 3)return 0;
		}
		return 0;
	}

	public void comportementFinalPhase() {
		joueur.setPhase(3);
		deplacementJoueurCoordonnee(9, 9);
		verificationWin();
	}

	public void affichageJeu() {
		int positionLJoueur = joueur.getPosition()[0];
		int positionCJoueur = joueur.getPosition()[1];
		String affichage = "";

		for (int l = 0; l <= 9; l++) {
			for (int c = 0; c <= 9; c++) {
				if (map[l][c] instanceof Ble && (positionLJoueur != l || positionCJoueur != c)) {
					affichage = affichage + " B " + " ";
				} else if (map[l][c] instanceof Ble && positionLJoueur == l && positionCJoueur == c) {
					affichage = affichage + "[B]" + " ";
				} else if (map[l][c] instanceof Pierre && (positionLJoueur != l || positionCJoueur != c)) {
					affichage = affichage + " P " + " ";
				} else if (map[l][c] instanceof Pierre && positionLJoueur == l && positionCJoueur == c) {
					affichage = affichage + "[P]" + " ";
				} else if (map[l][c] instanceof Bois && (positionLJoueur != l || positionCJoueur != c)) {
					affichage = affichage + " W " + " ";
				} else if (map[l][c] instanceof Bois && positionLJoueur == l && positionCJoueur == c) {
					affichage = affichage + "[W]" + " ";
				} else if (positionLJoueur == l && positionCJoueur == c) {
					affichage = affichage + "[-]" + " ";
				} else {
					affichage = affichage + " - " + " ";
				}
			}
			System.out.println(affichage);
			affichage = "";
		}
		joueur.debugJoueur();
	}

	public void setJoueur(Hero joueur) {
		this.joueur = joueur;
	}

	public Ressource[][] getMap() {
		return map;
	}

}
