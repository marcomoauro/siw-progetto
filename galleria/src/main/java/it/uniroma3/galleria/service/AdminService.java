package it.uniroma3.galleria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.galleria.model.Admin;
import it.uniroma3.galleria.repository.OperaRepository;
import it.uniroma3.galleria.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository userRepository; 

    public Iterable<Admin> findAll() {
        return this.userRepository.findAll();
    }
    
    @Transactional
    public void add(final Admin admin) throws Exception{
        this.userRepository.save(admin);    	
    }
    
    public Admin find(final Long id) {
    	return this.userRepository.findOne(id);
    }
    
    public Admin findByUsername(final String username){
    	return (Admin) this.userRepository.findByUsername(username);
    }

}
