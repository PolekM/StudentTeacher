package com.example.Intern.config;

import com.example.Intern.entity.Student;
import com.example.Intern.entity.Teacher;
import com.example.Intern.enums.Major;
import com.example.Intern.enums.Subject;
import com.example.Intern.repository.StudentRepository;
import com.example.Intern.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DbInit {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public DbInit(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Bean
    public void initTeacherStudentTable() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Marek", "Nowak", 40, "Marek@poczta.pl", Subject.MATH));
        teachers.add(new Teacher("Kamil", "Kowalski", 34, "Kamil@poczta.pl", Subject.HISTORY));
        teachers.add(new Teacher("Wojtek", "Nowicki", 52, "Wojtek@poczta.pl", Subject.MATH));
        teachers.add(new Teacher("Grzegorz", "Stary", 70, "Grzegorz@poczta.pl", Subject.ENGLISH));

        List<Student> students = new ArrayList<>();
        students.add(new Student("Wojtek", "Kotek", 20, "Wojtek@poczta.pl", Major.HISTORY));
        students.add(new Student("Mirek", "Sofa", 22, "Mirek@poczta.pl", Major.ENGLISH));
        students.add(new Student("Adam", "Greg", 21, "Adam@poczta.pl", Major.MATH));
        students.add(new Student("Jacek", "Kowalski", 19, "Jacek@poczta.pl", Major.ENGLISH));
        students.add(new Student("Sebastian", "Moc", 26, "Sebastian@poczta.pl", Major.MATH));

        studentRepository.saveAll(students);
        teacherRepository.saveAll(teachers);

    }


}
