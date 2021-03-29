package com.teaching.service.impl;

import com.teaching.mapper.TeacherCourseMapper;
import com.teaching.pojo.TeacherCourse;
import com.teaching.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    // 插入数据成功返回课程id
    // 失败返回 -1
    @Override
    public int save(TeacherCourse teacherCourse) {
        // 查询课程表该课程码是否存在
        int retId = teacherCourseMapper.insert(teacherCourse);
        return retId;
    }

    @Override
    public List<Integer> getAllJoinCourseId(Integer teacherId) {
        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = teacherCourseMapper.listAllJoinCourse(teacherId);
        return courseIdList;
    }
}
