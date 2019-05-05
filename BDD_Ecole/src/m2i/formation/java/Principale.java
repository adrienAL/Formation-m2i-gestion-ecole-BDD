package m2i.formation.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Principale {

	public static void main(String[] args) {
		
		
		//eleve 1	
		ArrayList<Note> notes = new ArrayList<Note>();
		notes.add(new Note(1, 11f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 1));
		notes.add(new Note(2, 15f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 1));
		notes.add(new Note(3, 17f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 1));
		notes.add(new Note(4, 10f, 2, LocalDate.of(2019, 05, 02), Matiere.FRANCAIS, 1));
		notes.add(new Note(5, 14f, 2, LocalDate.of(2019, 05, 02), Matiere.MATHS, 1));
		notes.add(new Note(6, 14f, 2, LocalDate.of(2019, 05, 02), Matiere.MATHS, 1));
		notes.add(new Note(7, 15f, 2, LocalDate.of(2019, 05, 02), Matiere.SPORT, 1));
		notes.add(new Note(8, 12f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 1));
		
		Eleve e1 = new Eleve(1, "DURAN", "Jacque", notes);
		DAO_Eleve daoe = new DAO_Eleve();

		daoe.Delete(1);
		daoe.Create(e1);
		
		
		//mise à jour de l'eleve 1
		Note n17 = new Note(9, 10f, 2, LocalDate.of(2019, 05, 02), Matiere.FRANCAIS, 1);
		Note n18 = new Note(10, 12f, 2, LocalDate.of(2019, 05, 02), Matiere.SPORT, 1);
		Note n19 = new Note(11, 17f, 2, LocalDate.of(2019, 05, 02), Matiere.FRANCAIS, 1);
		
		e1.AjouterNote(n17);
		e1.AjouterNote(n18);
		e1.AjouterNote(n19);
		
		daoe.Update(e1);
		
		
		//eleve 2
		ArrayList<Note> notes2 = new ArrayList<Note>();
		notes2.add(new Note(12, 3.5f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 2));
		notes2.add(new Note(13, 5f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 2));
		notes2.add(new Note(14, 7f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 2));
		notes2.add(new Note(15, 8f, 2, LocalDate.of(2019, 05, 02), Matiere.FRANCAIS, 2));
		notes2.add(new Note(16, 17f, 2, LocalDate.of(2019, 05, 02), Matiere.MATHS, 2));
		notes2.add(new Note(17, 14f, 2, LocalDate.of(2019, 05, 02), Matiere.MATHS, 2));
		notes2.add(new Note(18, 12f, 2, LocalDate.of(2019, 05, 02), Matiere.SPORT, 2));
		notes2.add(new Note(19, 5f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 2));
		
		Eleve e2 = new Eleve(2, "DUPON", "Elodie", notes2);
		
		daoe.Delete(2);
		daoe.Create(e2);
		
		
		//eleve 3
		ArrayList<Note> notes3 = new ArrayList<Note>();
		notes3.add(new Note(20, 13.5f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 3));
		notes3.add(new Note(21, 15f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 3));
		notes3.add(new Note(22, 17f, 2, LocalDate.of(2019, 05, 02), Matiere.HISTOIRE, 3));
		notes3.add(new Note(23, 18f, 2, LocalDate.of(2019, 05, 02), Matiere.FRANCAIS, 3));
		notes3.add(new Note(24, 7f, 2, LocalDate.of(2019, 05, 02), Matiere.MATHS, 3));
		notes3.add(new Note(25, 4f, 2, LocalDate.of(2019, 05, 02), Matiere.MATHS, 3));
		
		
		Eleve e3 = new Eleve(3, "JEAN", "Marie", notes3);
		
		daoe.Delete(3);
		daoe.Create(e3);
		
		
		//Affichage des elements recupéré dans la base de donnée
		for(int i= 0 ; i < daoe.RetreiveAll().size() ; i ++)
		{
			System.out.println(daoe.RetreiveAll().get(i));
		}
		
		
		
		
		
		
		
		Map<String,String> maRequet = Utilitaire.requetesSql("m2i.formation.java.Note");
		
		for(String cle : maRequet.keySet())
		{
			System.out.println(cle + ":   \t" + maRequet.get(cle));
		}
		
	}

}
