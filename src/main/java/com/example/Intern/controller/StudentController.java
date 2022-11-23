package com.example.Intern.controller;

import com.example.Intern.dto.student.RemoveTeacherStudentDto;
import com.example.Intern.dto.student.StudentAddTeacherDto;
import com.example.Intern.dto.student.StudentFilter;
import com.example.Intern.dto.teacher.TeacherDto;
import com.example.Intern.dto.student.StudentFindDto;
import com.example.Intern.dto.student.StudentUpdateDto;
import com.example.Intern.entity.Student;
import com.example.Intern.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/s")
    public List<StudentFindDto> getAllStudent() {
        return studentService.getAllStudent();
    }

    @PostMapping("/students")
    public List<Student> getByNameAndSurname(@RequestBody StudentFilter studentFilter) {

        return studentService.getByNameAndSurname(studentFilter);
    }

    @DeleteMapping("/students/remove/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/student/new")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PutMapping("/student/update/{id}")
    public void updateStudent(@PathVariable("id") Long id, @RequestBody StudentUpdateDto student) {
        student.setId(id);
        studentService.updateStudent(student);
    }

    @PostMapping("/student/addTeacher")
    public void addTeacherToStudent(@RequestBody StudentAddTeacherDto studentAddTeacherDto) {
        studentService.addTeacherToStudent(studentAddTeacherDto);
    }

    @GetMapping("/student/allTeachers/{id}")
    public List<TeacherDto> allStudentTeachers(@PathVariable("id") Long id,
                                               @RequestParam Integer pageNumber,
                                               @RequestParam Integer pageSize,
                                               @RequestParam String sortingParam){
        Sort sort = Sort.by(Sort.Direction.ASC, sortingParam);
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sort);
        return studentService.allStudentTeachers(id,pageRequest);
    }
    @PostMapping("/student/removeTeacher")
    public void removeTeacherFromStudent(@RequestBody RemoveTeacherStudentDto removeTeacherStudentDto){
        studentService.removeTeacherFromStudent(removeTeacherStudentDto);

    }
}

