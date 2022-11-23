package com.example.Intern.entity;

import com.example.Intern.enums.Major;
import com.example.Intern.enums.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3)
    private String name;
    private String surname;
    @Min(19)
    private int age;
    @Email
    private String email;
    private Subject subject;
    @ManyToMany(mappedBy = "teachers")
    private Set<Student> students;

    public Teacher(String name, String surname, int age, String email, Subject subject) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.subject = subject;
    }

    public void addStudentToTeacher(Student student) {
        students.add(student);
    }

}
