package erreur;

import matrix.Ressource;

public class ErreurInventairePierreException extends Exception{
	private static final long serialVersionUID = 1L;

	private Ressource ressource;
	
	public ErreurInventairePierreException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}

}
