package br.com.treinaweb.springspecifications.api.jobs.queryfilters;

import static br.com.treinaweb.springspecifications.core.specifications.JobSpec.*;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.treinaweb.springspecifications.core.models.Job;
import lombok.Data;

@Data
public class JobQueryFilter {

    private String title;

    private String location;

    private BigDecimal salaryGte;

    private BigDecimal salaryLte;

    private String skill;

    public Specification<Job> toSpecification() {
        return titleContains(title)
            .and(locationEquals(location))
            .and(salaryGreaterThanOrEqualTo(salaryGte))
            .and(salaryLessThanOrEqualTo(salaryLte))
            .and(skillNameEqual(skill));
    }
    
}
