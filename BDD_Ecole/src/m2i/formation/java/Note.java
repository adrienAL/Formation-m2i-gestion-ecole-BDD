package m2i.formation.java;

import java.time.LocalDate;

public class Note {
	
	//Attributs
	private float valeur;
	private int coef;
	private LocalDate date;
	private Matiere matiere;
	private int ID_Personne;
	private int ID_Note;
	
	
	//Constructeurs
	public Note(int id_note, float valeur, int coef, Matiere matiere, int id_personne) 
	{
		super();
		this.ID_Note = id_note;
		this.valeur = valeur;
		this.coef = coef;
		this.matiere = matiere;
		this.ID_Personne = id_personne;
	}
	
	public Note(int id_note, float valeur, int coef, LocalDate date, Matiere matiere, int id_personne) 
	{
		super();
		this.ID_Note = id_note;
		this.valeur = valeur;
		this.coef = coef;
		this.date = date;
		this.matiere = matiere;
		this.ID_Personne = id_personne;
	}
	
	public Note() {
		super();
	}
	
	//getteurs et setteurs
	public float getValeur() {
		return valeur;
	}
	public void setValeur(float valeur) {
		this.valeur = valeur;
	}
	public int getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public int getID_Personne() {
		return ID_Personne;
	}
	public void setID_Personne(int iD_Personne) {
		ID_Personne = iD_Personne;
	}
	public int getID_Note() {
		return ID_Note;
	}
	public void setID_Note(int iD_Note) {
		ID_Note = iD_Note;
	}
	
	

	@Override
	public String toString() {
		return "-"+matiere + ": \t" + valeur + "/20 - Coef: " + coef + "\t date: "+date+ "\n" ;
	}
	
	
	
	
	
}