package matrix;

public class Ressources {

	private int poids;
	private String id;

	public Ressources(int poids, String id) {
		this.poids = poids;
		this.id = id;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

}
