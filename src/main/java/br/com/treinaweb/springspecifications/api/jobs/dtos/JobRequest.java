package br.com.treinaweb.springspecifications.api.jobs.dtos;

import java.math.BigDecimal;
import java.util.List;

import br.com.treinaweb.springspecifications.core.enums.JobLevel;
import br.com.treinaweb.springspecifications.core.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    private String title;

    private String description;

    private String company;

    private String location;

    private JobType type;

    private JobLevel level;

    private BigDecimal salary;

    private List<String> skills;
    
}
