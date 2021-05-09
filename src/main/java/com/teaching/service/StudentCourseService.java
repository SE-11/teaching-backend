package com.teaching.service;

import com.teaching.pojo.StudentCourse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentCourseService {
    int save(StudentCourse studentCourse);
    List<Integer> getAllJoinCourseId(Integer studentId);
}
