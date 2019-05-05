package m2i.formation.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO_Eleve implements IDAO<Eleve> 
{
	final static String url = "jdbc:mysql://localhost:3306/projet_ecole?serverTimezone=UTC"; 
	final static String user = "root";
	final static String pwd = "" ;
	
	private static Connection _Cnn = Connexion.getConnection(url, user, pwd);

	@Override
	public int Create(Eleve obj) {
		int rep = -1;
		String chSql = "insert into eleve Values (?,?,?)";
		
		PreparedStatement ps;
		try {
			ps = _Cnn.prepareStatement(chSql);
			ps.setInt(1, obj.getID_Personne());
			ps.setString(2, obj.getNom());
			ps.setString(3, obj.getPrenom());
			
			rep = ps.executeUpdate();
			
			
			// Ajouter des notes de l'objet Eleve dans la table "eleve"
			DAO_Note daon = new DAO_Note();
			for (Note n : obj.getLstNotes()) 
			{
				daon.Create(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rep;
	}

	@Override
	public Eleve Retreive(int id) {
		Eleve rep = null;
		String chSql = "SELECT * FROM eleve WHERE ID_Eleve = ?";
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				int id_eleve = rs.getInt(1);
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				
				
				//Récupération des notes ayant l'id de l'élève dans la table note
				ArrayList<Note> notestmp = new ArrayList<Note>();
				ArrayList<Note> notes = new ArrayList<Note>();
				
				DAO_Note daon = new DAO_Note();
				daon.RetreiveAll();
				notestmp = daon.RetreiveAll();
				
				for(int i = 0 ; i < notestmp.size() ; i++)
				{
					if(notestmp.get(i).getID_Personne() == id)
					{
						notes.add(notestmp.get(i));						
					}
				}
				
				rep = new Eleve(id_eleve, nom, prenom, notes);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
		
	}

	@Override
	public List<Eleve> RetreiveAll() {
		List<Eleve> rep = new ArrayList<Eleve>();
		String chSql = "SELECT * FROM eleve";
		
		try {
			Statement s = _Cnn.createStatement();
			
			ResultSet rs = s.executeQuery(chSql);
			
			while(rs.next())
			{
				int id_eleve = rs.getInt(1);
				String nom = rs.getString(2);
				String prenom = rs.getString(3);
				
				ArrayList<Note> notestmp = new ArrayList<Note>();
				ArrayList<Note> notes = new ArrayList<Note>();
				
				DAO_Note daon = new DAO_Note();
				daon.RetreiveAll();
				notestmp = daon.RetreiveAll();
				
				for(int i = 0; i < notestmp.size() ; i++)
				{
					if(notestmp.get(i).getID_Personne() == id_eleve)
						notes.add(notestmp.get(i));
				}
				
				
				rep.add(new Eleve(id_eleve, nom, prenom, notes));				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
	}

	@Override
	public int Update(Eleve obj) 
	{
		int rep = -1;
		
		if(this.Retreive(obj.getID_Personne()) == null)
		{
			return this.Create(obj);
		}
		
		String chSql = "update eleve set Nom = ?, Prenom = ? where ID_Eleve = ?";
		
		try {
			PreparedStatement ps  = _Cnn.prepareStatement(chSql);
			ps.setString(1, obj.getNom());
			ps.setString(2, obj.getPrenom());
			ps.setInt(3, obj.getID_Personne());
			
			DAO_Note daon = new DAO_Note();
			daon.SupprTouteLesNotes(obj.getID_Personne());
			
			for(Note n : obj.getLstNotes())
				daon.Create(n);
			
			
			rep = ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rep;
		
		
	}

	@Override
	public int Delete(int id) {
		int rep = -1;
		String chSql = "delete from eleve where ID_Eleve =?";
		
		PreparedStatement ps;
		
		try {
			ps = _Cnn.prepareStatement(chSql);
			ps.setInt(1, id);
			
			DAO_Note daon = new DAO_Note();
			daon.SupprTouteLesNotes(id);
			rep = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
		
		
	}

}
