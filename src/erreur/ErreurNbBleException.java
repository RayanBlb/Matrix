package erreur;

import matrix.Ressource;

public class ErreurNbBleException extends Exception{

	private static final long serialVersionUID = 1L;

	private Ressource ressource;

	public ErreurNbBleException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}

}
