package com.ass.system.model.course;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Course {
    private int courseId;

    private String courseName;
}
