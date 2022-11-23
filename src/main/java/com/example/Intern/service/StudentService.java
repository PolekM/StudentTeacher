package com.example.Intern.service;

import com.example.Intern.dto.student.RemoveTeacherStudentDto;
import com.example.Intern.dto.student.StudentAddTeacherDto;
import com.example.Intern.dto.student.StudentFilter;
import com.example.Intern.dto.teacher.TeacherDto;
import com.example.Intern.dto.student.StudentFindDto;
import com.example.Intern.dto.student.StudentUpdateDto;
import com.example.Intern.entity.Student;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface StudentService {

    List<Student> getByNameAndSurname(StudentFilter studentFilter);

    void deleteStudent(Long id);

    List<StudentFindDto> getAllStudent();

    void addStudent(Student student);

    void updateStudent(StudentUpdateDto student);

    void addTeacherToStudent(StudentAddTeacherDto studentAddTeacherDto);

    List<TeacherDto> allStudentTeachers(Long id, PageRequest sort);

    void removeTeacherFromStudent(RemoveTeacherStudentDto removeTeacherStudentDto);


}
