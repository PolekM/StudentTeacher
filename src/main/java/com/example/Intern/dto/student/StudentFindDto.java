package com.example.Intern.dto.student;

import com.example.Intern.enums.Major;
import lombok.*;

@Getter
@Setter
public class StudentFindDto {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private Major major;
}
