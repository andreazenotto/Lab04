package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		StudenteDAO s = new StudenteDAO();
		Studente st = s.getStudenteByMatricola(146101);
		System.out.println(st.toString());
		
	}

}
