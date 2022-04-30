package matrix;

import java.util.*;

import erreur.ErreurDeplacementException;
import erreur.ErreurInventaireBoisException;

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

	public void debugPersonnage() {
		System.out.println("----------------------");
		System.out.println("Size bois : " + bois.size());
		System.out.println("Size ble : " + ble.size());
		System.out.println("Poids : " + poids);
		System.out.println("x : " + position[0] + " y : " + position[1]);
		if (pierre != null) {
			System.out.println("Pierre OK");
		}
		if (farine != null) {
			System.out.println("Farine OK");
		}
		if (feu != null) {
			System.out.println("Feu OK");
		}
		System.out.println("----------------------");
	}

	public void seDeplacer(Direction direction) throws ErreurDeplacementException {
		switch (direction) {
		case HAUT:
			if ((position[0] -= 1) < 0) {
				position[0] += 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case BAS:
			if ((position[0] += 1) > 9) {
				position[0] -= 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case GAUCHE:
			if ((position[1] -= 1) < 0) {
				position[1] += 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		case DROITE:
			if ((position[1] += 1) > 9) {
				position[1] -= 1;
				throw new ErreurDeplacementException(direction);
			}
			break;
		}
		nbPartie += 1;
	}

	public void prendre(Ressource r) throws ErreurInventaireBoisException{
		if (r instanceof Ble) {
			if (ble.size() <= 9) {
				ble.add(r);
				poids += r.getPoids();
			}
		} else if (r instanceof Pierre) {
			if (pierre == null && farine == null) {
				pierre = new Pierre("pierre");
				poids += r.getPoids();
			}
		} else if (r instanceof Bois) {
			if (bois.size() <= 4 && farine != null) {
				bois.add(r);
				poids += r.getPoids();
			}else {
				throw new ErreurInventaireBoisException(r);
			}
		}
	}

	public void jeter(Ressource r) {
		if (r instanceof Pierre) {
			if (pierre != null) {
				pierre = null;
				poids -= r.getPoids();
			}
		} else if (r instanceof Bois) {
			bois.remove(bois.size() - 1);
			poids -= r.getPoids();
		} else if (r instanceof Ble) {
			ble.remove(ble.size() - 1);
			poids -= r.getPoids();
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
		if (ble.size() == 10 && farine == null && pierre != null) {
			ble.clear();
			farine = new ObjetManufacture("farine");
			poids -= 10;
		}
	}

	public void faireFeu() {
		if (bois.size() == 5 && feu == null) {
			bois.clear();
			feu = new ObjetManufacture("feu");
			poids -= 10;
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
