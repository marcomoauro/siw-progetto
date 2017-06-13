package it.uniroma3.galleria.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {
	@Id
	private String username;
	@NotNull()
	private String password;
	private boolean enabled;
	@OneToMany
	private List<Ruolo> ruoli;
	
	public User(){
		this.enabled=true;
		this.ruoli = new ArrayList<>();
		this.ruoli.add(new Ruolo("ROLE_USER"));
	}
	
	public User(String username,String password){
		this.username = username;
		this.password = password;
		this.enabled = true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
