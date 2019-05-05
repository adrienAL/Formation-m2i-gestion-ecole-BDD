package m2i.formation.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class DAO_Note implements IDAO<Note> 
{
	final static String url = "jdbc:mysql://localhost:3306/projet_ecole?serverTimezone=UTC"; 
	final static String user = "root";
	final static String pwd = "" ;
	
	//Connection à la base de donnée projet_ecole
	private static Connection _Cnn = Connexion.getConnection(url, user, pwd);

	@Override
	public int Create(Note obj) {
		int rep = -1; //retournera le nombre de fois que la requete est effectué
		
		//Squelete de la requete SQL
		String chSql_insert = "Insert into note VALUES (?,?,?,?,?,?)" ;
		
		try {
			//Variable qui permet de remplir le squelet de requete 
			PreparedStatement ps = _Cnn.prepareStatement(chSql_insert);
			
			//Remplissage de la requete SQL
			ps.setInt(1, obj.getID_Note());					
			ps.setFloat(2, obj.getValeur());
			ps.setInt(3, obj.getCoef());
			ps.setString(4, obj.getMatiere().name());
			ps.setInt(5, obj.getID_Personne());
			ps.setObject(6, obj.getDate());
			
			//Execution de la requete
			rep = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//on retourne le nombre de fois que la requete a ete effectue
		return rep;
	}

	@Override
	public Note Retreive(int id) {
		Note rep = null;
		String chSql = "Select * from note WHERE ID_Note = ? " ;
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			ps.setInt(1, id);
			
			//Sauvegarde de la reponse de la base de donnée
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				int _ID_Note = rs.getInt(1);
				float _Valeur = rs.getFloat(2);
				int _Coef = rs.getInt(3);
				Matiere _Mat = Matiere.valueOf(rs.getString(4));
				int _ID_Eleve = rs.getInt(5);
				LocalDate date = rs.getObject(6, LocalDate.class); // pour récuperer la date
				
				
				rep = new Note(_ID_Note,_Valeur,_Coef,date,_Mat,_ID_Eleve);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return rep;
	}

	@Override
	public ArrayList<Note> RetreiveAll() {
		ArrayList<Note> rep = new ArrayList<Note>();
		String chSql = "select * from note";
		
		try {
			Statement s = _Cnn.createStatement();
			ResultSet rs = s.executeQuery(chSql);
			
			while(rs.next())
			{
				int _ID_Note = rs.getInt(1);
				float _Valeur = rs.getFloat(2);
				int _Coef = rs.getInt(3);
				Matiere _Mat = Matiere.valueOf(rs.getString(4));
				int _ID_Eleve = rs.getInt(5);
				LocalDate date = rs.getObject(6, LocalDate.class);
				
				rep.add(new Note(_ID_Note,_Valeur,_Coef,date,_Mat,_ID_Eleve));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rep;
		
	}

	@Override
	public int Update(Note obj) {
		int rep = -1;
		String chSql = "update note set Note = ?, coef = ?, Matiere = ?, ID_Eleve=?, date=? where ID_Note = ?";
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			
			ps.setFloat(1, obj.getValeur());
			ps.setInt(2, obj.getCoef());
			ps.setString(3, obj.getMatiere().name());
			ps.setInt(4, obj.getID_Personne());
			ps.setObject(5, obj.getDate());
			ps.setInt(6, obj.getID_Note());
			
			rep=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;

	}

	@Override
	public int Delete(int id) {
		int rep = -1;
		String chSql = "delete from note where ID_Note = ?";
		
		PreparedStatement ps;
		try {
			ps = _Cnn.prepareStatement(chSql);
			ps.setInt(1, id);
			rep=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
	}
	
	public int SupprTouteLesNotes(int id_eleve)
	{
		int rep = -1;
		String chSql = "delete from note where ID_Eleve = ?";
		
		try {
			PreparedStatement ps = _Cnn.prepareStatement(chSql);
			ps.setInt(1, id_eleve);
			rep = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rep;
	}

}
