package it.polito.tdp.lab04.model;

public class Corso {

	private String codins;
	private Integer crediti;
	private String nome;
	private Integer periodo;
	
	public Corso(String codins, Integer crediti, String nomeCorso, Integer periodo) {
		super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nomeCorso;
		this.periodo = periodo;
	}

	public String getCodins() {
		return codins;
	}

	public void setCodins(String codice) {
		this.codins = codice;
	}

	public Integer getCrediti() {
		return crediti;
	}

	public void setCrediti(Integer crediti) {
		this.crediti = crediti;
	}

	public String getNomeCorso() {
		return nome;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nome = nomeCorso;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return codins + ", " + crediti + " crediti, " + nome + ", " + periodo + " peridodo didattico";
	}
	
}
