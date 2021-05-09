package com.teaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.HomeworkMapper;
import com.teaching.pojo.Homework;
import com.teaching.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;

    @Override
    public int save(Homework homework) {
        homeworkMapper.insert(homework);
        return homework.getId();
    }

    @Override
    public List<Homework> listHomeworkByCourseId(Integer courseId) {
        QueryWrapper<Homework> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        return homeworkMapper.selectList(queryWrapper);
    }
}
