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
	
	public Opera findOne(Long id){
		return this.operaRepository.findOne(id);
	}

	@Transactional
	public void add(final Opera opera) {
		this.operaRepository.save(opera);
	}

	public void delete(long id) {
		this.operaRepository.delete(id);

	}

	public List<Opera> findByStanza(Stanza stanza){
		return this.operaRepository.findByStanza(stanza);
	}

	public List<Opera> findByAutore(Long id) {
		return this.operaRepository.findByAutoreId(id);
	}

	public List<Opera> findByStanza(Long id) {
		return this.operaRepository.findByStanzaId(id);
	}

}
