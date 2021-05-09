package com.teaching.service;

import com.teaching.pojo.Homework;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HomeworkService {
    int save(Homework homework);
    List<Homework> listHomeworkByCourseId(Integer courseId);
}
