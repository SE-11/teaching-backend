package com.teaching.controller;

import com.teaching.pojo.Announce;
import com.teaching.pojo.Course;
import com.teaching.service.CourseService;
import com.teaching.vo.CourseTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{id}")
    public CourseTeacherVO getCourseById(@PathVariable("id") Integer id) {
        CourseTeacherVO courseTeacherVO = courseService.getCourseInfoByCourseId(id);
        return courseTeacherVO;
    }

    @PostMapping("/course/addAnnounce")
    public Announce saveAnnounce(@RequestBody Announce announce) {
        int annId = courseService.saveAnnounce(announce);
        return courseService.getAnnById(annId);
    }

    @GetMapping("/course/{id}/listAnn")
    public List<Announce> listAnnByCourseId(@PathVariable("id") Integer id) {
        return courseService.listAnnByCourseId(id);
    }
}
