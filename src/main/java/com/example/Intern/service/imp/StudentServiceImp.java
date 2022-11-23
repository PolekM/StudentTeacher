package com.example.Intern.service.imp;

import com.example.Intern.dto.student.RemoveTeacherStudentDto;
import com.example.Intern.dto.student.StudentAddTeacherDto;
import com.example.Intern.dto.student.StudentFilter;
import com.example.Intern.dto.student.StudentFindDto;
import com.example.Intern.dto.student.StudentUpdateDto;
import com.example.Intern.dto.teacher.TeacherDto;
import com.example.Intern.entity.Student;
import com.example.Intern.entity.Teacher;
import com.example.Intern.repository.StudentRepository;
import com.example.Intern.repository.TeacherRepository;
import com.example.Intern.service.StudentService;
import com.example.Intern.specification.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {

    StudentRepository studentRepository;
    TeacherRepository teacherRepository;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> getByNameAndSurname(StudentFilter studentFilter) {
        return studentRepository.findAll(Specification.where(StudentSpecification.findByName(studentFilter).and(StudentSpecification.findBySurname(studentFilter))));
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentFindDto> getAllStudent() {
        return studentRepository.findAll().stream().map(this::studentFindDto).collect(Collectors.toList());
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(StudentUpdateDto student) {
        studentRepository.findById(student.getId()).ifPresent(student1 -> {
            student1.setName(student.getName());
            student1.setSurname(student.getSurname());
            student1.setAge(student.getAge());
            student1.setMajor(student.getMajor());
            student1.setEmail(student.getEmail());
            studentRepository.save(student1);
        });

    }

    @Override
    public void addTeacherToStudent(StudentAddTeacherDto studentAddTeacherDto) {
        System.out.println(studentAddTeacherDto.getStudentID());
        Optional<Student> student = studentRepository.findById(studentAddTeacherDto.getStudentID());
        Optional<Teacher> teacher = teacherRepository.findById(studentAddTeacherDto.getTeacherId());
        if (student.isPresent() && teacher.isPresent()) {
            Student student1 = student.get();
            teacher.get().setStudents(Set.of(student.get()));
            student1.addTeacherToStudent(teacher.get());
            studentRepository.save(student1);
        }

    }

    @Override
    public void removeTeacherFromStudent(RemoveTeacherStudentDto removeTeacherStudentDto) {
        Optional<Teacher> teacher = teacherRepository.findById(removeTeacherStudentDto.getTeacherId());
        Optional<Student> student = studentRepository.findById(removeTeacherStudentDto.getStudentID());
        if (teacher.isPresent() && student.isPresent()) {
            Student student1 = student.get();
            student1.getTeachers().remove(teacher.get());
            teacher.get().getStudents().remove(student.get());
            studentRepository.save(student1);
        }
    }

    @Override
    public List<TeacherDto> allStudentTeachers(Long id, PageRequest pagsorteable) {
        return studentRepository.findAllTeachers(pagsorteable, id)
                .stream()
                .map(this::getTeacherDto)
                .collect(Collectors.toList());
    }


    private StudentFindDto studentFindDto(Student student) {
        StudentFindDto studentFindDto = new StudentFindDto();
        studentFindDto.setAge(student.getAge());
        studentFindDto.setEmail(student.getEmail());
        studentFindDto.setMajor(student.getMajor());
        studentFindDto.setName(student.getName());
        studentFindDto.setSurname(student.getSurname());
        studentFindDto.setId(student.getId());
        return studentFindDto;
    }

    private TeacherDto getTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setAge(teacher.getAge());
        teacherDto.setName(teacher.getName());
        teacherDto.setSurname(teacher.getSurname());
        teacherDto.setId(teacher.getId());
        return teacherDto;
    }

}
