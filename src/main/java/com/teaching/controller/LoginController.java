package com.teaching.controller;

import com.teaching.pojo.Student;
import com.teaching.pojo.Teacher;
import com.teaching.service.StudentService;
import com.teaching.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    // 注册成功 errorCode == 1 返回用户 id
    // 否则 返回 errorCode -1
    @PostMapping("/regist/student")
    public Map<String, Object> studentRegistered(@RequestBody Student student) {
        int errorCode = studentService.registered(student);
        Map<String, Object> retMap = new HashMap<>();

        if(errorCode == -1) {
            retMap.put("errorCode", errorCode);
        } else {
            retMap.put("errorCode", 1);
            retMap.put("studentId", errorCode);
        }
        return retMap;
    }

    @PostMapping("/regist/teacher")
    public Map<String, Object> teacherRegistered(@RequestBody Teacher teacher) {
        //
        int errorCode = teacherService.registered(teacher);
        Map<String, Object> retMap = new HashMap<>();
        if(errorCode == -1) {
            retMap.put("errorCode", errorCode);
        } else {
            retMap.put("errorCode", 1);
            retMap.put("teacherId", errorCode);
        }
        return retMap;
    }
}
