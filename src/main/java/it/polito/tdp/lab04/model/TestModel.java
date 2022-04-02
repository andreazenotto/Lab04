package it.polito.tdp.lab04.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		/*
		 * 	Write here your test model
		 */
		List<Corso> temp = model.getTuttiICorsi();
		for(Corso c: temp) {
			System.out.println(c.toString());
		}

	}

}
