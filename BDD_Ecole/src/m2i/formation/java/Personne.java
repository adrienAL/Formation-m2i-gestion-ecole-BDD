package m2i.formation.java;

public abstract class Personne {
	protected String nom;
	protected String prenom;
	protected int ID_Personne;
	
	public Personne(int id_personne, String nom, String prenom) {
		super();
		this.ID_Personne = id_personne;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getID_Personne() {
		return ID_Personne;
	}
	public void setID_Personne(int iD_Personne) {
		ID_Personne = iD_Personne;
	}
	
	
}