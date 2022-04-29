package matrix;

import erreur.ErreurInventaireBleException;
import erreur.ErreurInventaireBoisException;
import erreur.ErreurInventairePierreException;

public class Jeu {
	private Hero personnage;
	private Ressource[][] map;

	public Jeu(String hero, Ressource[][] map) {
		personnage = new Hero(hero);
		this.map = map;
	}

	public void jouer() {
		deplacementPersonnage();
	}

	public void algoCheck() {
		int[] coordonnee = personnage.getPosition();

		try {
			personnage.prendre(map[coordonnee[0]][coordonnee[1]]);
		} catch (ErreurInventairePierreException e) {
			
		} catch (ErreurInventaireBoisException e) {
			try {
				personnage.faireFeu();
			} catch (Exception z) {

			}
		} catch (ErreurInventaireBleException e) {
			try {
				personnage.faireFarine();
			} catch (Exception z) {

			}
		}
	}

	public void deplacementPersonnage() {
		for (int y = 0; y <= 9; y++) {

			for (int x = 0; x <= 9; x++) {

				if (y % 2 == 0) {

					try {
						personnage.debugCoordonnee();
						personnage.seDeplacer(Direction.DROITE);
					} catch (Exception e) {
					}

				} else {

					try {
						personnage.debugCoordonnee();
						personnage.seDeplacer(Direction.GAUCHE);
					} catch (Exception e) {
					}

				}
			}

			try {
				personnage.seDeplacer(Direction.BAS);
			} catch (Exception e) {
			}

		}
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
