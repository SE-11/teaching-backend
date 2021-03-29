package com.teaching.service;

import com.teaching.pojo.TeacherCourse;

import java.util.List;

public interface TeacherCourseService {
    int save(TeacherCourse teacherCourse);
    List<Integer> getAllJoinCourseId(Integer teacherId);
}
