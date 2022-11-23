package com.example.Intern.entity;

import com.example.Intern.enums.Major;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
public class Student implements Serializable {
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
    private Major major;
    @ManyToMany
    @JoinTable(
            name = "student_teacher",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"))
    private Set<Teacher> teachers;

    public Student(String name, String surname, int age, String email, Major major) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.major = major;
    }

    public void addTeacherToStudent(Teacher teacher) {
        teachers.add(teacher);
    }

}
