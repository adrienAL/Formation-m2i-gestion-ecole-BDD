package m2i.formation.java;

import java.util.ArrayList;

public class Eleve extends Personne implements ICalcul {
	private ArrayList<Note> lstNotes = new ArrayList<Note>();
	
	//Constructeurs
	public Eleve(int id_eleve, String nom, String prenom, ArrayList<Note> lstNotes) {
		super(id_eleve, nom, prenom);
		this.lstNotes = lstNotes;
	}
	
	public Eleve(int id_eleve, String nom, String prenom) {
		super(id_eleve, nom, prenom);
	}
	
	
	//Méthode qui calcule la moyenne de l'eleve
	@Override
	public float Moyenne() {
		if (lstNotes.size() == 0)
			return -99;
		
		float somme = 0f;
		float sommeCoef = 0;
		
		for (Note elem:lstNotes) {
			somme += elem.getValeur();
			sommeCoef += elem.getCoef();
		}
		return somme / sommeCoef;
	}
	
	//Méthode qui retourne la moyenne de l'eleve par matiere
	@Override
	public float Moyenne(Matiere mat) {
		if (lstNotes.size() == 0)
			return -99;
		
		float somme = 0f;
		float sommeCoef = 0;
		
		for (Note elem:lstNotes) {
			if (elem.getMatiere() == mat) {
				somme += elem.getValeur();
				sommeCoef += elem.getCoef();
			}
		}
		return somme / sommeCoef;
	}
	
	//Affiche nom prénom et note de l'eleve
	public void Afficher() {
		System.out.println(this.prenom + " " + this.nom);
		
		for (Matiere elem:Matiere.values()) {
				System.out.println(elem + "\t" + Moyenne(elem));
		}
	}

	//getteur et setteur
	public ArrayList<Note> getLstNotes() {
		return lstNotes;
	}
	public void setLstNotes(ArrayList<Note> lstNotes) {
		this.lstNotes = lstNotes;
	}
	
	//Méthode qui permet d'ajoutter une note à l'eleve
	public void AjouterNote(Note n) {
		this.lstNotes.add(n);
	}

	@Override
	public String toString() {
		String retour =  "Nom: \t" + getNom() + "\nPrenom: " + getPrenom() + "\n" ;
		for(int i = 0; i<lstNotes.size() ; i++)
			retour += lstNotes.get(i);
		return retour;
				
	}
	
	
	
	
	
}