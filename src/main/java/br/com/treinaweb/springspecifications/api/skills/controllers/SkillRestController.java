package br.com.treinaweb.springspecifications.api.skills.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.treinaweb.springspecifications.api.skills.dtos.SkillRequest;
import br.com.treinaweb.springspecifications.api.skills.dtos.SkillResponse;
import br.com.treinaweb.springspecifications.api.skills.mappers.SkillMapper;
import br.com.treinaweb.springspecifications.core.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillRestController {

    private static final ResponseStatusException SKILL_NOT_FOUND_EXCEPTION = 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill não encontrada");

    private final SkillMapper skillMapper;
    private final SkillRepository skillRepository;

    @GetMapping
    public List<SkillResponse> findAll() {
        return skillRepository.findAll()
            .stream()
            .map(skillMapper::toSkillResponse)
            .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillResponse create(@RequestBody SkillRequest skillRequest) {
        var skill = skillMapper.toSkill(skillRequest);
        skillRepository.save(skill);
        return skillMapper.toSkillResponse(skill);
    }

    @GetMapping("/{id}")
    public SkillResponse findById(@PathVariable Long id) {
        var skill = skillRepository.findById(id)
            .orElseThrow(() -> SKILL_NOT_FOUND_EXCEPTION);
        return skillMapper.toSkillResponse(skill);
    }

    @PutMapping("/{id}")
    public SkillResponse update(@PathVariable Long id, @RequestBody SkillRequest skillRequest) {
        var skill = skillRepository.findById(id)
            .orElseThrow(() -> SKILL_NOT_FOUND_EXCEPTION);
        BeanUtils.copyProperties(skillRequest, skill, "id");
        skillRepository.save(skill);
        return skillMapper.toSkillResponse(skill);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        var skill = skillRepository.findById(id)
            .orElseThrow(() -> SKILL_NOT_FOUND_EXCEPTION);
        skillRepository.delete(skill);
    }
    
}
