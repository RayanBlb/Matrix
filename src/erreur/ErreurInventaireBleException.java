package erreur;

import matrix.Ressource;

public class ErreurInventaireBleException extends Exception{

	private static final long serialVersionUID = 1L;

	private Ressource ressource;

	public ErreurInventaireBleException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}

}
