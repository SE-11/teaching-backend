package com.teaching.service.impl;

import com.teaching.mapper.StudentCourseMapper;
import com.teaching.pojo.StudentCourse;
import com.teaching.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Autowired
    private StudentCourseMapper studentCourseMapper;

    // 插入数据成功返回课程id
    // 失败返回 -1
    @Override
    public int save(StudentCourse studentCourse) {
        // 查询课程表该课程码是否存在
        int retId = studentCourseMapper.insert(studentCourse);
        return retId;
    }

    @Override
    public List<Integer> getAllJoinCourseId(Integer studentId) {
        List<Integer> courseIdList = new ArrayList<>();
        courseIdList = studentCourseMapper.listAllJoinCourse(studentId);
        return courseIdList;
    }
}
