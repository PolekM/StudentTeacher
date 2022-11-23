package com.example.Intern.controller;

import com.example.Intern.dto.student.RemoveTeacherStudentDto;
import com.example.Intern.dto.teacher.TeacherFindAllStudentDto;
import com.example.Intern.dto.student.StudentAddDto;
import com.example.Intern.dto.teacher.TeacherFindDto;
import com.example.Intern.dto.teacher.TeacherFilter;
import com.example.Intern.dto.teacher.TeacherUpdateDto;
import com.example.Intern.entity.Teacher;
import com.example.Intern.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/t")
    public List<TeacherFindDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/teachers")
    public List<Teacher> getByNameAndSurname(@RequestBody TeacherFilter teacherFilter) {

        return teacherService.getByNameAndSurname(teacherFilter);
    }

    @DeleteMapping("/teachers/remove/{id}")
    public void deleteTeacher(@PathVariable("id") Long id) {
        teacherService.deleteTeacher(id);
    }

    @PostMapping("/teacher/new")
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    @PutMapping("/teacher/update/{id}")
    public void updateTeacher(@PathVariable("id") Long id, @RequestBody TeacherUpdateDto teacher) {
        teacher.setId(id);
        teacherService.updateTeacher(teacher);
    }

    @PostMapping("/teacher/addStudent")
    public void addStudentToTeacher(@RequestBody StudentAddDto studentAddDto) {
        teacherService.addStudentToTeacher(studentAddDto);
    }

    @DeleteMapping("/teacher/removeStudent")
    public void removeStudentFromTeacher(@RequestBody RemoveTeacherStudentDto removeTeacherStudentDto) {
        teacherService.removeStudentFromTeacher(removeTeacherStudentDto);
    }

    @GetMapping("/teacher/allStudent/{id}")
    public List<TeacherFindAllStudentDto> allTeacherStudent(@PathVariable("id") Long id,
                                                            @RequestParam Integer pageNumber,
                                                            @RequestParam Integer pageSize,
                                                            @RequestParam String sortingParam) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortingParam);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return teacherService.allTeacherStudent(id, pageRequest);
    }


}
