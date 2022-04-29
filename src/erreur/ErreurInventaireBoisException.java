package erreur;

import matrix.Ressource;

public class ErreurInventaireBoisException extends Exception{
	private static final long serialVersionUID = 1L;

	private Ressource ressource;
	
	public ErreurInventaireBoisException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}

}
