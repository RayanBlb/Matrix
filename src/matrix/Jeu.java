package matrix;

import java.util.*;

import erreur.ErreurInventaireBoisException;
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
		comportementSimpleJoueur();
	}

	public void verificationJoueur() {
		int[] coordonnee = personnage.getPosition();

		try {
			personnage.prendre(map[coordonnee[0]][coordonnee[1]]);
			map[coordonnee[0]][coordonnee[1]] = null;
		} catch (ErreurInventaireBoisException e) {
			Pair coordoBois = new Pair(coordonnee[0], coordonnee[1]);
			boisCoordonnee.add(coordoBois);
		}catch (Exception e) {
			
		}
		
		if (personnage.getBle().size() >= 10) {
			personnage.faireFarine();
		} else if (personnage.getBois().size() >= 5) {
			personnage.faireFeu();
		} else if (personnage.getPierre() != null && personnage.getFarine() != null
				&& map[coordonnee[0]][coordonnee[1]] == null) {
			personnage.jeter(personnage.getPierre());
			map[coordonnee[0]][coordonnee[1]] = new Pierre("pierre");
		}

		personnage.debugPersonnage();
	}

	public void deplacementJoueurCoordonnee(int x, int y) {

		if (personnage.getPosition()[0] > x) {
			while (x != personnage.getPosition()[0]) {
				try {
					personnage.seDeplacer(Direction.HAUT);
				} catch (Exception e) {
				}
			}
		} else {
			while (x != personnage.getPosition()[0]) {
				try {
					personnage.seDeplacer(Direction.BAS);
				} catch (Exception e) {
				}
			}
		}

		if (personnage.getPosition()[1] > y) {
			while (y != personnage.getPosition()[1]) {
				try {
					personnage.seDeplacer(Direction.GAUCHE);
				} catch (Exception e) {
				}
			}
		} else {
			while (y != personnage.getPosition()[1]) {
				try {
					personnage.seDeplacer(Direction.DROITE);
				} catch (Exception e) {
				}
			}
		}
		verificationJoueur();
	}
	
	public void verificationWin() {
		if(personnage.fairePain() == true && personnage.getPosition()[0] == 9 && personnage.getPosition()[1] == 9) {
			System.out.println("You win avec : "+personnage.getNbPartie()+" Coups !!!");
		}
	}
	
	//Comportement 1
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
						personnage.seDeplacer(Direction.DROITE);
					} catch (Exception e) {
					}
				} else {
					try {
						personnage.seDeplacer(Direction.GAUCHE);
					} catch (Exception e) {
					}
				}

			}

			verificationJoueur();

			try {
				personnage.seDeplacer(Direction.BAS);
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
	
	//Fin comportement 1

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
