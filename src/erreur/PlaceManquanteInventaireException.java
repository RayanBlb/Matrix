package erreur;

import matrix.Ressource;

public class PlaceManquanteInventaireException extends Exception{
private static final long serialVersionUID = 1L;

	private Ressource ressource;
	
	public PlaceManquanteInventaireException(Ressource r) {
		super();
		this.ressource = r;
	}

	public Ressource getRessource() {
		return ressource;
	}
	
}
