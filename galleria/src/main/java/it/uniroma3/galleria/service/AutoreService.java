package it.uniroma3.galleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.Autore;
import it.uniroma3.galleria.repository.AutoreRepository;

@Service
public class AutoreService {
	@Autowired
	private AutoreRepository ar;
	public Iterable<Autore> findAll(){
		return this.ar.findAll();
	}
	public Autore findOne(Long id){
		return this.ar.findOne(id);
	}
	@Transactional
	public void add(final Autore autore){
		this.ar.save(autore);
	}
	@Transactional
	public void delete(Long id){
		this.ar.delete(id);
	}

}