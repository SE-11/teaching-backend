package com.teaching.controller;

import com.teaching.pojo.Course;
import com.teaching.pojo.Teacher;
import com.teaching.pojo.TeacherCourse;
import com.teaching.service.CourseService;
import com.teaching.service.TeacherCourseService;
import com.teaching.service.TeacherService;
import com.teaching.vo.CourseTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherCourseService teacherCourseService;

    @GetMapping("/teacher/{id}")
    public Teacher getTeacherById(@PathVariable("id") Integer id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return teacher;
    }

    /*
    * 教师创建课程
    * */
    @PostMapping("/teacher/saveCourse")
    public Course saveCourse(@RequestBody Course course) {
        int courseId = courseService.save(course);
        Course retCourse = courseService.getById(courseId);
        return retCourse;
    }

    /*
    * 教师请求所教的所有课程
    * 返回 VO 为 CourseTeacherVO
    * */
    @GetMapping("/teacher/listCourse/{id}")
    public List<CourseTeacherVO> listCourseInfoByTeacherId(@PathVariable("id") Integer id) {
        List<CourseTeacherVO> courseTeacherVOS = new ArrayList<CourseTeacherVO>();
        courseTeacherVOS = courseService.listByTeacherId(id);
        return courseTeacherVOS;
    }

    /*
     * 教师请求学习的所有课程
     * */
    @GetMapping("/teacher/listJoinCourse/{id}")
    public List<CourseTeacherVO> listJoinCourse(@PathVariable("id") Integer id) {
        // 根据教师 id 找到该教师所选的所有课程的 id
        List<CourseTeacherVO> courseTeacherVOS = new ArrayList<>();
        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = teacherCourseService.getAllJoinCourseId(id);
        // 根据这些id找到所有对应课程的 CourseTeacherVO
        courseTeacherVOS = courseService.listCourseInfoByCourseIdList(courseIdList);
        return courseTeacherVOS;
    }

    /*
     * 根据课程id查找
     * */
    //Get Course Info by course id
    @GetMapping("/teacher/getCourseInfoById/{id}")
    public CourseTeacherVO getCourseInfoByCourseId(@PathVariable("id") Integer id) {
        CourseTeacherVO courseTeacherVO = new CourseTeacherVO();
        courseTeacherVO = courseService.getCourseInfoByCourseId(id);
        return courseTeacherVO;
    }

    // 参数：教师id   邀请码
    // teacher 加入课程
    // 返回课程信息
    @PostMapping("/teacher/joinCourse")
    public Map<String, Object> joinCourse(@RequestBody Map<String, Object> map) {
        // 校验是否存在课程码
        String invitationCode = (String) map.get("invitationCode");
        int state = courseService.getIdByInvitationCode(invitationCode);
        Map<String, Object> retMap = new HashMap<>();

        if(state == -1) {
            retMap.put("errorCode", state);
            retMap.put("msg", "课程不存在");
        } else {
            // state 存course id
            // 向 TeacherCourse 表插入一条记录
            // 插入成功后返回课程的Info

            int teacherId = Integer.parseInt((String) map.get("teacherId"));
            TeacherCourse teacherCourse = new TeacherCourse(teacherId, state);
            int insertId = teacherCourseService.save(teacherCourse);

            // get course info
            CourseTeacherVO courseTeacherVO = new CourseTeacherVO();
            courseTeacherVO = courseService.getCourseInfoByCourseId(state);
            retMap.put("errorCode", state);
            retMap.put("msg", "加入课堂成功!");
            retMap.put("data", courseTeacherVO);
        }
        return retMap;
    }
}
