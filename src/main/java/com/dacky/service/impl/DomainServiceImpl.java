package com.dacky.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacky.entity.Domain;
import com.dacky.repository.DomainRepository;
import com.dacky.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService{

	
	@Autowired
	private DomainRepository domainRepository;
	
	@Override
	public List<Domain> getAllDomain() {
		return domainRepository.findAll();
	}

	@Override
	public Domain save(String domain) {
		Domain newDomain= new Domain();
		newDomain.setDomainUrl(domain);
		return domainRepository.save(newDomain);
	}

	@Override
	public void delete(Long id) {
		domainRepository.deleteById(id);
	}

}
