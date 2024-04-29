package br.com.treinaweb.springspecifications.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.springspecifications.core.models.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    
}
