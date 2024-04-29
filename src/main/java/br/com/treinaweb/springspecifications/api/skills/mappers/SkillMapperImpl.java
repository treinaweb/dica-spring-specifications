package br.com.treinaweb.springspecifications.api.skills.mappers;

import org.springframework.stereotype.Component;

import br.com.treinaweb.springspecifications.api.skills.dtos.SkillRequest;
import br.com.treinaweb.springspecifications.api.skills.dtos.SkillResponse;
import br.com.treinaweb.springspecifications.core.models.Skill;

@Component
public class SkillMapperImpl implements SkillMapper {

    @Override
    public SkillResponse toSkillResponse(Skill skill) {
        return SkillResponse.builder()
            .id(skill.getId())
            .name(skill.getName())
            .build();
    }

    @Override
    public Skill toSkill(SkillRequest skillRequest) {
        return Skill.builder()
            .name(skillRequest.getName())
            .build();
    }
    
}
