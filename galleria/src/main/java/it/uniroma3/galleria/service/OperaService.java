package it.uniroma3.galleria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.Opera;
import it.uniroma3.galleria.model.Stanza;
import it.uniroma3.galleria.repository.OperaRepository;

@Service
public class OperaService {

	@Autowired
	private OperaRepository operaRepository; 

	public Iterable<Opera> findAll() {
		return this.operaRepository.findAll();
	}

	@Transactional
	public void add(final Opera opera) {
		this.operaRepository.save(opera);
	}
	public Opera findbyId(Long id) {
		return this.operaRepository.findOne(id);
	}

	public void delete(long id) {
		this.operaRepository.delete(id);

	}

	public List<Opera> findByStanza(Stanza stanza){
		return this.operaRepository.findByStanza(stanza);
	}

}
