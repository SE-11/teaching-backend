package com.teaching.service;

import com.teaching.pojo.Course;

import java.util.List;

public interface CourseService {
    // 插入一条 Course
    int save(Course course);

    // getById
    Course getById(Integer id);

    List<Course> listByTeacherId(Integer id);
}
