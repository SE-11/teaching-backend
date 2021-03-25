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

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> map) {
        // 查询是否存在该手机号码
        String phone =  (String) map.get("phone");
        String password = (String) map.get("password");
        int studentId = studentService.getByPhone(phone);
        int teacherId = teacherService.getByPhone(phone);
        Map<String, Object> retMap = new HashMap<>();

        if((studentId == -1) && (teacherId == -1)) {
            retMap.put("errorCode", -1);
            retMap.put("msg", "该用户不存在");
        } else if(studentId != -1) {
            // 该用户为学生
            // 校验密码
            if(studentService.checkPassword(studentId, password)) {
                retMap.put("errorCode", 1);
                retMap.put("msg", "登陆成功");
                retMap.put("userType", "student");
                retMap.put("id", studentId);
            } else {
                retMap.put("errorCode", -2);
                retMap.put("msg", "密码错误");
            }
        } else {
            // 该用户为教师
            // 校验密码
            if(teacherService.checkPassword(teacherId, password)) {
                retMap.put("errorCode", 1);
                retMap.put("msg", "登陆成功");
                retMap.put("userType", "teacher");
                retMap.put("id", teacherId);
            } else {
                retMap.put("errorCode", -2);
                retMap.put("msg", "密码错误");
            }
        }
        return retMap;
    }
}
