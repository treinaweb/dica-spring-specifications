package br.com.treinaweb.springspecifications.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.springspecifications.core.models.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{

    List<Skill> findAllByNameIn(List<String> names);
    
}
