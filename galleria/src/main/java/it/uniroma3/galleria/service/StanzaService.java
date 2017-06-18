package it.uniroma3.galleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.repository.StanzaRepository;

@Service
public class StanzaService {

	@Autowired
	private StanzaRepository stanzaRepository;
	
	public Iterable<Stanza> findAll(){
		return this.stanzaRepository.findAll();
	}
	
	public Stanza findOne(Long id){
		return this.stanzaRepository.findOne(id);
	}
	
	@Transactional
	public void add(final Stanza stanza){
		this.stanzaRepository.save(stanza);
	}
	
	@Transactional
	public void delete(Long id){
		this.stanzaRepository.delete(id);
	}
	
}
