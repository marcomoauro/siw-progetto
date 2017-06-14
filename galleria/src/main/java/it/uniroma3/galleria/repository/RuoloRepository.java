package it.uniroma3.galleria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.galleria.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {  
	
	public List<Ruolo> findByRuolo(String ruolo);
	
}

