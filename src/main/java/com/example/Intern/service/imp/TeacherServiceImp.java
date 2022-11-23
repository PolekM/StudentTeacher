package com.example.Intern.service.imp;

import com.example.Intern.dto.student.RemoveTeacherStudentDto;
import com.example.Intern.dto.student.StudentAddDto;
import com.example.Intern.dto.teacher.TeacherFilter;
import com.example.Intern.dto.teacher.TeacherFindAllStudentDto;
import com.example.Intern.dto.teacher.TeacherFindDto;
import com.example.Intern.dto.teacher.TeacherUpdateDto;
import com.example.Intern.entity.Student;
import com.example.Intern.entity.Teacher;
import com.example.Intern.repository.StudentRepository;
import com.example.Intern.repository.TeacherRepository;
import com.example.Intern.service.TeacherService;
import com.example.Intern.specification.TeacherSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeacherServiceImp implements TeacherService {

    TeacherRepository teacherRepository;
    StudentRepository studentRepository;

    @Autowired
    public TeacherServiceImp(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<TeacherFindDto> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(this::getTeacherDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Teacher> getByNameAndSurname(TeacherFilter teacherFilter) {
        return teacherRepository.findAll(Specification.where(TeacherSpecification.findByName(teacherFilter)).and(TeacherSpecification.findBySurname(teacherFilter)));
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(TeacherUpdateDto teacher) {

        teacherRepository.findById(teacher.getId()).ifPresent(teacher1 -> {
            teacher1.setName(teacher.getName());
            teacher1.setSurname(teacher.getSurname());
            teacher1.setAge(teacher.getAge());
            teacher1.setSubject(teacher.getSubject());
            teacher1.setEmail(teacher.getEmail());
            teacherRepository.save(teacher1);
        });


    }

    @Override
    public void addStudentToTeacher(StudentAddDto studentAddDto) {
        Optional<Student> student = studentRepository.findById(studentAddDto.getStudentID());
        Optional<Teacher> teacher = teacherRepository.findById(studentAddDto.getTeacherId());
        if (student.isPresent() && teacher.isPresent()) {
            Teacher teacher1 = teacher.get();
            student.get().setTeachers(Set.of(teacher.get()));
            teacher1.addStudentToTeacher(student.get());
            teacherRepository.save(teacher1);
        }

    }

    @Override
    public void removeStudentFromTeacher(RemoveTeacherStudentDto removeTeacherStudentDto) {
        Optional<Teacher> teacher = teacherRepository.findById(removeTeacherStudentDto.getTeacherId());
        Optional<Student> student = studentRepository.findById(removeTeacherStudentDto.getStudentID());
        if (teacher.isPresent() && student.isPresent()) {
            Teacher teacher1 = teacher.get();
            teacher1.getStudents().remove(student.get());
            student.get().getTeachers().remove(teacher1);
            teacherRepository.save(teacher1);
        }
    }

    @Override
    public List<TeacherFindAllStudentDto> allTeacherStudent(Long id, PageRequest pageRequest) {
        return teacherRepository.findAllStudent(pageRequest, id)
                .stream()
                .map(this::getStudentDto)
                .collect(Collectors.toList());
    }

    private TeacherFindAllStudentDto getStudentDto(Student student) {
        TeacherFindAllStudentDto studentDto = new TeacherFindAllStudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setSurname(student.getSurname());
        studentDto.setAge(student.getAge());
        return studentDto;
    }

    private TeacherFindDto getTeacherDto(Teacher teacher) {
        TeacherFindDto teacherFindDto = new TeacherFindDto();
        teacherFindDto.setAge(teacher.getAge());
        teacherFindDto.setEmail(teacher.getEmail());
        teacherFindDto.setId(teacher.getId());
        teacherFindDto.setName(teacher.getName());
        teacherFindDto.setSurname(teacher.getSurname());
        teacherFindDto.setSubject(teacher.getSubject());
        return teacherFindDto;
    }

}

