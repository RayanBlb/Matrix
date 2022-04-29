package matrix;

import java.util.*;

import erreur.ErreurDeplacementException;
import erreur.ErreurInventaireBleException;
import erreur.ErreurInventaireBoisException;
import erreur.ErreurInventairePierreException;

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
	
	public void debugCoordonnee() {
		System.out.println("x : "+position[0]+" y : "+position[1]);
	}
	
	public void seDeplacer(Direction direction) throws ErreurDeplacementException {
		switch (direction) {
		case HAUT:
			position[0] -= 1;
			if (position[0] < 0) {
				position[0] += 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case BAS:
			position[0] += 1;
			if (position[0] > 9) {
				position[0] -= 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case GAUCHE:
			position[1] -= 1;
			if (position[1] < 0) {
				position[1] += 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case DROITE:
			position[1] += 1;
			if (position[1] > 9) {
				position[1] -= 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		}
	}

	public void prendre(Ressource r) throws ErreurInventairePierreException, ErreurInventaireBoisException, ErreurInventaireBleException {
		if (r instanceof Pierre) {
			if (pierre == null) {
				pierre = new Pierre("pierre");
				poids += r.getPoids();
				if (poids > 13) {
					poids -= r.getPoids();
					throw new ErreurInventairePierreException(r);
				}
			} else {
				throw new ErreurInventairePierreException(r);
			}
		} else if (r instanceof Bois) {
			if (bois.size() <= 4) {
				bois.add(r);
				poids += r.getPoids();
				if (poids > 13) {
					poids -= 2;
					throw new ErreurInventaireBoisException(r);
				}
			} else {
				throw new ErreurInventaireBoisException(r);
			}
		} else if (r instanceof Ble) {
			if (ble.size() <= 9) {
				ble.add(r);
				poids += r.getPoids();
				if (poids > 13) {
					poids -= r.getPoids();
					throw new ErreurInventaireBleException(r);
				}
			} else {
				throw new ErreurInventaireBleException(r);
			}
		}
	}

	public void jeter(Ressource r) {
		if (r instanceof Pierre) {
			if (pierre != null) {
				pierre = null;
				poids -= r.getPoids();
			} else if (r instanceof Bois) {
				bois.remove(bois.size() - 1);
				poids -= r.getPoids();
			} else if (r instanceof Ble) {
				ble.remove(ble.size() - 1);
				poids -= r.getPoids();
			}
		}
	}

	public boolean fairePain() {
		boolean resultat;

		if (farine != null && feu != null) {
			resultat = true;
		} else {
			resultat = false;
		}
		return resultat;
	}

	public void faireFarine() {
		if (ble.size() == 10) {
			suppressionList(ble);
			farine = new ObjetManufacture("farine");
			poids -= 10;
		}
	}

	public void faireFeu() {
		if (bois.size() == 5) {
			suppressionList(bois);
			farine = new ObjetManufacture("feu");
			poids -= 10;
		}
	}

	public void suppressionList(List<Ressource> liste) {
		for (int i = 0; i < liste.size(); i++) {
			liste.remove(i);
		}
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
