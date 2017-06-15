package it.uniroma3.galleria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.galleria.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long> {

    List<Admin> findByUsername(String username);
}