package com.teaching.controller;

import com.teaching.pojo.Student;
import com.teaching.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return student;
    }
}
