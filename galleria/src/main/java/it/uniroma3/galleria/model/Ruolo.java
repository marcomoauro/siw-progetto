package it.uniroma3.galleria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Ruolo {
	
	@Id
	@Column(name="role")
	private String ruolo;
	
	public Ruolo(){}
	
	public Ruolo(String ruolo){
		this.ruolo=ruolo;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
	

}
