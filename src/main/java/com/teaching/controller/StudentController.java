package com.teaching.controller;

import com.teaching.pojo.Student;
import com.teaching.pojo.StudentCourse;
import com.teaching.service.CourseService;
import com.teaching.service.StudentCourseService;
import com.teaching.service.StudentService;
import com.teaching.vo.CourseTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Integer id) {
        Student student = studentService.getStudentById(id);
        return student;
    }

    /*
     * 学生请求学习的所有课程
     * */
    @GetMapping("/student/listJoinCourse/{id}")
    public List<CourseTeacherVO> listJoinCourse(@PathVariable("id") Integer id) {
        // 根据学生 id 找到该教师所选的所有课程的 id
        List<CourseTeacherVO> courseTeacherVOS = new ArrayList<>();
        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = studentCourseService.getAllJoinCourseId(id);
        // 根据这些id找到所有对应课程的 CourseTeacherVO
        courseTeacherVOS = courseService.listCourseInfoByCourseIdList(courseIdList);
        return courseTeacherVOS;
    }

    @PostMapping("/student/joinCourse")
    public Map<String, Object> joinCourse(@RequestBody Map<String, Object> map) {
        String invitationCode = (String) map.get("invitationCode");
        int state = courseService.getIdByInvitationCode(invitationCode);
        Map<String, Object> retMap = new HashMap<>();

        if(state == -1) {
            retMap.put("errorCode", state);
            retMap.put("msg", "课程不存在");
        } else {
            int studentId = Integer.parseInt((String) map.get("studentId"));
            StudentCourse studentCourse = new StudentCourse(studentId, state);
            int insertId = studentCourseService.save(studentCourse);

            CourseTeacherVO courseTeacherVO = new CourseTeacherVO();
            courseTeacherVO = courseService.getCourseInfoByCourseId(state);
            retMap.put("errorCode", state);
            retMap.put("msg", "加入课堂成功!");
            retMap.put("data", courseTeacherVO);
        }
        return retMap;
    }
}
