package com.teaching.controller;

import com.teaching.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/listAllStudent")
    public @ResponseBody
    Object listAllStudent() {
        return studentService.listAllStudent();
    }
}
