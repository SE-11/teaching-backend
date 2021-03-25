package com.teaching.controller;

import com.teaching.pojo.Teacher;
import com.teaching.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teacher/{id}")
    public Teacher getTeacherById(@PathVariable("id") Integer id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return teacher;
    }
}
