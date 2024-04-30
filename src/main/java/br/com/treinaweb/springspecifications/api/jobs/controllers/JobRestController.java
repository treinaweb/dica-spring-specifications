package br.com.treinaweb.springspecifications.api.jobs.controllers;

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

import br.com.treinaweb.springspecifications.api.jobs.dtos.JobRequest;
import br.com.treinaweb.springspecifications.api.jobs.dtos.JobResponse;
import br.com.treinaweb.springspecifications.api.jobs.mappers.JobMapper;
import br.com.treinaweb.springspecifications.api.jobs.queryfilters.JobQueryFilter;
import br.com.treinaweb.springspecifications.core.repositories.JobRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobRestController {

    private static final ResponseStatusException JOB_NOT_FOUND_EXCEPTION = 
        new ResponseStatusException(HttpStatus.NOT_FOUND, "Job não encontrado");

    private final JobMapper jobMapper;
    private final JobRepository jobRepository;

    @GetMapping
    public List<JobResponse> findAll(JobQueryFilter filter) {
        return jobRepository.findAll(filter.toSpecification())
            .stream()
            .map(jobMapper::toJobResponse)
            .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobResponse create(@RequestBody JobRequest jobRequest) {
        var job = jobMapper.toJob(jobRequest);
        jobRepository.save(job);
        return jobMapper.toJobResponse(job);
    }

    @GetMapping("/{id}")
    public JobResponse findById(@PathVariable Long id) {
        var job = jobRepository.findById(id)
            .orElseThrow(() -> JOB_NOT_FOUND_EXCEPTION);
        return jobMapper.toJobResponse(job);
    }

    @PutMapping("/{id}")
    public JobResponse update(@PathVariable Long id, @RequestBody JobRequest jobRequest) {
        var job = jobRepository.findById(id)
            .orElseThrow(() -> JOB_NOT_FOUND_EXCEPTION);
        var jobData = jobMapper.toJob(jobRequest);
        BeanUtils.copyProperties(jobData, job, "id");
        jobRepository.save(job);
        return jobMapper.toJobResponse(job);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        var job = jobRepository.findById(id)
            .orElseThrow(() -> JOB_NOT_FOUND_EXCEPTION);
        jobRepository.delete(job);
    }
    
}
