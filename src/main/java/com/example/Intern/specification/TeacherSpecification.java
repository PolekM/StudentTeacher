package com.example.Intern.specification;

import com.example.Intern.dto.teacher.TeacherFilter;
import com.example.Intern.entity.Teacher;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class TeacherSpecification {

    public static Specification<Teacher> findByName(TeacherFilter teacherFilter) {
        return new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")), teacherFilter.getName().toLowerCase());
            }
        };

    }

    public static Specification<Teacher> findBySurname(TeacherFilter teacherFilter) {
        return new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(criteriaBuilder.lower(root.get("surname")), teacherFilter.getSurname().toLowerCase());
            }
        };

    }

}
