package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public Studente getStudenteByMatricola(int matricola) {
		return this.studenteDAO.getStudenteByMatricola(matricola);
	}
	
	public List<Corso> getCorsiStudente(int matricola) {
		return this.studenteDAO.getCorsiStudente(matricola);
	}
	
	public boolean inscriviStudenteACorso(int matricola, Corso corso) {
		return this.corsoDAO.inscriviStudenteACorso(this.studenteDAO.getStudenteByMatricola(matricola), corso);
	}
}
