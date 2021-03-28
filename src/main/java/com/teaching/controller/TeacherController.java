package com.teaching.controller;

import com.teaching.pojo.Course;
import com.teaching.pojo.Teacher;
import com.teaching.service.CourseService;
import com.teaching.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/teacher/{id}")
    public Teacher getTeacherById(@PathVariable("id") Integer id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return teacher;
    }

    @PostMapping("/teacher/saveCourse")
    public Course saveCourse(@RequestBody Course course) {
        int courseId = courseService.save(course);
        Course retCourse = courseService.getById(courseId);
        return retCourse;
    }

    @GetMapping("/teacher/listCourse/{id}")
    public List<Course> listCourseByTeacherId(@PathVariable("id") Integer id) {
        List<Course> courseList = new ArrayList<Course>();
        courseList = courseService.listByTeacherId(id);
        return courseList;
    }

}
