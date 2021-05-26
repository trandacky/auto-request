package com.dacky.service;

import java.util.List;

import com.dacky.entity.Domain;

public interface DomainService {

	List<Domain> getAllDomain();

	Domain save(String domain);

	void delete(Long id);
	
}
