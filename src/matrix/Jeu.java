package matrix;

import java.util.*;
import outils.Pair;

public class Jeu {
	private Hero personnage;
	private Ressource[][] map;

	static List<Pair> boisCoordonnee = new ArrayList<Pair>();

	public Jeu(String hero, Ressource[][] map) {
		personnage = new Hero(hero);
		this.map = map;
	}

	public void jouer() {
		deplacementJoueurPermierTour();
		comportementJoueurDeuxiemeTour();
		deplacementJoueurDeuxiemeTour(9, 9);
		if(personnage.fairePain() == true) {
			System.out.println("You Win : "+personnage.getNbPartie());
		}
	}

	public void comportementJoueurDeuxiemeTour() {
		for (int i = 0; i < boisCoordonnee.size(); i++) {
			Pair vTempo = boisCoordonnee.get(i);
			int x = vTempo.getL();
			int y = vTempo.getR();
			deplacementJoueurDeuxiemeTour(x, y);
		}
	}
	
	public void comportementJoueur() {
		int[] coordonnee = personnage.getPosition();

		if (personnage.getBle().size() >= 10) {
			personnage.faireFarine();
		} else if (personnage.getBois().size() >= 5) {
			personnage.faireFeu();
		} else if (personnage.getPierre() != null && personnage.getFarine() != null
				&& map[coordonnee[0]][coordonnee[1]] == null) {
			personnage.jeter(personnage.getPierre());
			map[coordonnee[0]][coordonnee[1]] = new Pierre("pierre");
		}

		try {
			personnage.prendre(map[coordonnee[0]][coordonnee[1]]);
			map[coordonnee[0]][coordonnee[1]] = null;
		} catch (Exception e) {
			Pair coordoBois = new Pair(coordonnee[0], coordonnee[1]);
			boisCoordonnee.add(coordoBois);
		}
		personnage.debugPersonnage();
	}

	public void deplacementJoueurPermierTour() {
		for (int y = 0; y <= 9; y++) {
			for (int x = 0; x < 9; x++) {
				if (y % 2 == 0) {
					try {
						comportementJoueur();
						personnage.seDeplacer(Direction.DROITE);
					} catch (Exception e) {
					}
				} else {
					try {
						comportementJoueur();
						personnage.seDeplacer(Direction.GAUCHE);
					} catch (Exception e) {
						System.out.println("Tes");
					}
				}
			}
			try {
				comportementJoueur();
				personnage.seDeplacer(Direction.BAS);
			} catch (Exception e) {
			}
		}
	}
	
	public void deplacementJoueurDeuxiemeTour(int x, int y) {
		if (personnage.getPosition()[0] > x) {
			while (x != personnage.getPosition()[0]) {
				try {
					personnage.seDeplacer(Direction.HAUT);
				} catch (Exception e) {
				}
			}
			comportementJoueur();
		} else {
			while (x != personnage.getPosition()[0]) {
				try {
					personnage.seDeplacer(Direction.BAS);
				} catch (Exception e) {
				}
			}
			comportementJoueur();
		}

		if (personnage.getPosition()[1] > y) {
			while(y != personnage.getPosition()[1]) {
				try {
					personnage.seDeplacer(Direction.GAUCHE);
				} catch (Exception e) {
				}
			}
			comportementJoueur();
		} else {
			while(y != personnage.getPosition()[1]) {
				try {
					personnage.seDeplacer(Direction.DROITE);
				} catch (Exception e) {
				}
			}
			comportementJoueur();
		}
		comportementJoueur();
	}

	public Hero getPersonnage() {
		return personnage;
	}

	public void setPersonnage(Hero personnage) {
		this.personnage = personnage;
	}

	public Ressource[][] getMap() {
		return map;
	}

}
