package br.com.treinaweb.springspecifications.api.jobs.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.treinaweb.springspecifications.api.jobs.dtos.JobRequest;
import br.com.treinaweb.springspecifications.api.jobs.dtos.JobResponse;
import br.com.treinaweb.springspecifications.core.models.Job;
import br.com.treinaweb.springspecifications.core.models.Skill;
import br.com.treinaweb.springspecifications.core.repositories.SkillRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JobMapperImpl implements JobMapper {

    private final SkillRepository skillRepository;

    @Override
    public JobResponse toJobResponse(Job job) {
        return JobResponse.builder()
            .id(job.getId())
            .title(job.getTitle())
            .description(job.getDescription())
            .company(job.getCompany())
            .location(job.getLocation())
            .type(job.getType())
            .level(job.getLevel())
            .salary(job.getSalary())
            .skills(skillsToSkillNames(job.getSkills()))
            .build();
    }

    @Override
    public Job toJob(JobRequest jobRequest) {
        return Job.builder()
            .title(jobRequest.getTitle())
            .description(jobRequest.getDescription())
            .company(jobRequest.getCompany())
            .location(jobRequest.getLocation())
            .type(jobRequest.getType())
            .level(jobRequest.getLevel())
            .salary(jobRequest.getSalary())
            .skills(skillRepository.findAllByNameIn(jobRequest.getSkills()))
            .build();
    }

    private List<String> skillsToSkillNames(List<Skill> skills) {
        return skills.stream().map(skill -> skill.getName()).toList();
    }
    
}
