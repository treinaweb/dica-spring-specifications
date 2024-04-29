package br.com.treinaweb.springspecifications.api.skills.mappers;

import br.com.treinaweb.springspecifications.api.skills.dtos.SkillRequest;
import br.com.treinaweb.springspecifications.api.skills.dtos.SkillResponse;
import br.com.treinaweb.springspecifications.core.models.Skill;

public interface SkillMapper {

    SkillResponse toSkillResponse(Skill skill);

    Skill toSkill(SkillRequest skillRequest);
    
}
