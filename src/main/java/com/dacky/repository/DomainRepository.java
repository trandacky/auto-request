package com.dacky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dacky.entity.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Long>{

}
