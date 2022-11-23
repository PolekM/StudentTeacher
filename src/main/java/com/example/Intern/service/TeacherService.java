package com.example.Intern.service;

import com.example.Intern.dto.student.RemoveTeacherStudentDto;
import com.example.Intern.dto.student.StudentAddDto;
import com.example.Intern.dto.teacher.TeacherFindAllStudentDto;
import com.example.Intern.dto.teacher.TeacherFindDto;
import com.example.Intern.dto.teacher.TeacherFilter;
import com.example.Intern.dto.teacher.TeacherUpdateDto;
import com.example.Intern.entity.Teacher;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TeacherService {

    void deleteTeacher(Long id);

    List<Teacher> getByNameAndSurname(TeacherFilter teacherFilter);

    List<TeacherFindDto> getAllTeachers();

    void addTeacher(Teacher teacher);

    void updateTeacher(TeacherUpdateDto teacher);

    void addStudentToTeacher(StudentAddDto studentAddDto);

    void removeStudentFromTeacher(RemoveTeacherStudentDto removeTeacherStudentDto);

    List<TeacherFindAllStudentDto> allTeacherStudent(Long id, PageRequest pageRequest);
}
