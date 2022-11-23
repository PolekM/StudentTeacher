package com.example.Intern.dto.teacher;

import com.example.Intern.enums.Subject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherUpdateDto {

    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private Subject subject;
}
