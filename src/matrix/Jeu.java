package matrix;

import erreur.ErreurInventaireBleException;
import erreur.ErreurInventaireBoisException;
import erreur.ErreurInventairePierreException;
import erreur.ErreurNbBleException;
import erreur.ErreurNbBoisException;
import erreur.ErreurNbPierreException;

public class Jeu {
	private Hero personnage;
	private Ressource[][] map;

	public Jeu(String hero, Ressource[][] map) {
		personnage = new Hero(hero);
		this.map = map;
	}

	public void jouer() {
		deplacementJoueur();
	}

	public void comportementJoueur() {
		int[] coordonnee = personnage.getPosition();

		if (personnage.getBle().size() >= 10) {
			personnage.faireFarine();
		} else if (personnage.getBois().size() >= 5) {
			personnage.faireFeu();
		}

		try {
			personnage.prendre(map[coordonnee[0]][coordonnee[1]]);
		} catch (ErreurInventairePierreException e) {
		} catch (ErreurInventaireBoisException e) {
		} catch (ErreurInventaireBleException e) {
		} catch (ErreurNbPierreException e) {
		} catch (ErreurNbBoisException e) {
		} catch (ErreurNbBleException e) {
		}
		map[coordonnee[0]][coordonnee[1]] = null;
	}

	public void deplacementJoueur() {
		for (int y = 0; y <= 9; y++) {

			for (int x = 0; x <= 9; x++) {

				if (y % 2 == 0) {

					try {
						comportementJoueur();
						personnage.debugPersonnage();
						personnage.seDeplacer(Direction.DROITE);
					} catch (Exception e) {
					}

				} else {

					try {
						comportementJoueur();
						personnage.debugPersonnage();
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
