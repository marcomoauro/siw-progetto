package it.uniroma3.galleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.repository.StanzaRepository;

@Service
public class StanzaService {

	@Autowired
	private StanzaRepository sr;
	
	public Iterable<Stanza> findAll(){
		return this.sr.findAll();
	}
	
	public Stanza findOne(Long id){
		return this.sr.findOne(id);
	}
	
	@Transactional
	public void add(final Stanza stanza){
		this.sr.save(stanza);
	}
	
	@Transactional
	public void delete(Long id){
		this.sr.delete(id);
	}

	
}
