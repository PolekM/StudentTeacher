package com.example.Intern.dto.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveTeacherStudentDto {
    private Long teacherId;
    private Long studentID;
}
