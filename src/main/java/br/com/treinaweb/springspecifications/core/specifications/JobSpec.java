package br.com.treinaweb.springspecifications.core.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;

import br.com.treinaweb.springspecifications.core.models.Job;

public class JobSpec {

    public static Specification<Job> titleContains(String title) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(title)) {
                return null;
            }
            return builder.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Job> locationEquals(String location) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(location)) {
                return null;
            }
            return builder.equal(root.get("location"), location);
        };
    }

    public static Specification<Job> salaryGreaterThanOrEqualTo(BigDecimal salary) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(salary)) {
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get("salary"), salary);
        };
    }
    
    public static Specification<Job> salaryLessThanOrEqualTo(BigDecimal salary) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(salary)) {
                return null;
            }
            return builder.lessThanOrEqualTo(root.get("salary"), salary);
        };
    }

    public static Specification<Job> skillNameEqual(String skillName) {
        return (root, query, builder) -> {
            if (ObjectUtils.isEmpty(skillName)) {
                return null;
            }
            return root.join("skills")
                .get("name")
                .in(skillName);
        };
    }
    
}
