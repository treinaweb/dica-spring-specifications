package br.com.treinaweb.springspecifications.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.treinaweb.springspecifications.core.models.Job;

public interface JobRepository extends 
    JpaRepository<Job, Long>, 
    JpaSpecificationExecutor<Job> 
{
    
}
