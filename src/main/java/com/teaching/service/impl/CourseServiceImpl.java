package com.teaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.CourseMapper;
import com.teaching.pojo.Course;
import com.teaching.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    // 插入成功 返回 id
    @Override
    public int save(Course course) {
        courseMapper.insert(course);
        int id = course.getCourseId();
        return id;
    }

    @Override
    public Course getById(Integer id) {
        Course course = courseMapper.selectById(id);
        return course;
    }

    @Override
    public List<Course> listByTeacherId(Integer id) {
        List<Course> courseList = new ArrayList<Course>();
        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>();
        queryWrapper.eq("teacher_id", id);
        courseList = courseMapper.selectList(queryWrapper);
        return courseList;
    }
}
