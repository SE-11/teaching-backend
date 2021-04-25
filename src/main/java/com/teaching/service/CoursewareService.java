package com.teaching.service;

import com.teaching.pojo.Courseware;

import java.util.List;

public interface CoursewareService {
    int save(Courseware courseware);
    List<Courseware> listCoursewareByCourseId(Integer courseId);
}
