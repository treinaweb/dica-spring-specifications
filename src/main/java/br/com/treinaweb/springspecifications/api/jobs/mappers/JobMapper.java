package br.com.treinaweb.springspecifications.api.jobs.mappers;

import br.com.treinaweb.springspecifications.api.jobs.dtos.JobRequest;
import br.com.treinaweb.springspecifications.api.jobs.dtos.JobResponse;
import br.com.treinaweb.springspecifications.core.models.Job;

public interface JobMapper {

    JobResponse toJobResponse(Job job);

    Job toJob(JobRequest jobRequest);
    
}
