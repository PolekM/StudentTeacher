package com.example.Intern.dto.student;

import com.example.Intern.enums.Major;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUpdateDto {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private Major major;
}
