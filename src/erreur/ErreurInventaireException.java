package erreur;

import matrix.Ressource;

public class ErreurInventaireException extends Exception{
	private static final long serialVersionUID = 1L;

	private Ressource ressource;
	
	public ErreurInventaireException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}
	
}
