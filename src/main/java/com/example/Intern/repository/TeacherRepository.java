package com.example.Intern.repository;

import com.example.Intern.entity.Student;
import com.example.Intern.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {

    @Query("select s from Student s join s.teachers t where t.id =:teacherId")
    Page<Student> findAllStudent(PageRequest pageable, @Param("teacherId") Long id);


}
