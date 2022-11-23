package com.example.Intern.repository;

import com.example.Intern.entity.Student;
import com.example.Intern.entity.Teacher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    @Query("select t from Teacher t join t.students s where s.id =:studentId")
    List<Teacher> findAllTeachers(PageRequest pageable, @Param("studentId") Long id);


}
