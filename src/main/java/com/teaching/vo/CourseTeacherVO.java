package com.teaching.vo;

import com.teaching.pojo.Course;
import lombok.Data;

@Data
public class CourseTeacherVO extends Course {
    private String avatar;
    private String university;
    private String teacherName;
}
