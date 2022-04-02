package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	List<Studente> studenti;
	
	public StudenteDAO() {
		this.studenti = new LinkedList<>();
		this.getStudenti();
	}

	public void getStudenti() {
		
		final String sql = "SELECT * FROM studente";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			this.studenti.clear();
			
			while (rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				this.studenti.add(s);
			}

			conn.close();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}		
	}
	
	public Studente getStudenteByMatricola(int matricola) {
		for(Studente s: this.studenti) {
			if(s.getMatricola()==matricola)
				return s;
		}
		return null;
	}
	
	public List<Corso> getCorsiStudente(int matricola) {

		final String sql = "SELECT c.codins, c.crediti, c.nome, c.pd\r "
				+ "FROM corso AS c, iscrizione AS i\r "
				+ "WHERE c.codins=i.codins AND i.matricola=?";

		List<Corso> corsi = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsi.add(c);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
}
