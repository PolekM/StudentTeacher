package com.example.Intern.specification;

import com.example.Intern.dto.student.StudentFilter;
import com.example.Intern.entity.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentSpecification {

    public static Specification<Student> findByName(StudentFilter studentFilter) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")), studentFilter.getName().toLowerCase());
            }
        };
    }

    public static Specification<Student> findBySurname(StudentFilter studentFilter) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(criteriaBuilder.lower(root.get("surname")), studentFilter.getSurname().toLowerCase());
            }
        };
    }
}
