package com.teaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.CoursewareMapper;
import com.teaching.pojo.Courseware;
import com.teaching.service.CoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursewareServiceImpl implements CoursewareService {
    @Autowired
    private CoursewareMapper coursewareMapper;

    @Override
    public int save(Courseware courseware) {
        coursewareMapper.insert(courseware);
        return courseware.getId();
    }

    @Override
    public List<Courseware> listCoursewareByCourseId(Integer courseId) {
        QueryWrapper<Courseware> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        return coursewareMapper.selectList(queryWrapper);
    }
}
