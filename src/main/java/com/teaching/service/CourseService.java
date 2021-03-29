package com.teaching.service;

import com.teaching.pojo.Course;
import com.teaching.vo.CourseTeacherVO;

import java.util.List;

public interface CourseService {
    // 插入一条 Course
    int save(Course course);

    // getById
    Course getById(Integer id);


    List<CourseTeacherVO> listByTeacherId(Integer teacherId);

    // get course info by Course id
    CourseTeacherVO getCourseInfoByCourseId(Integer courseId);

    // get course id by invitation Code
    int getIdByInvitationCode(String invitationCode);

    List<CourseTeacherVO> listCourseInfoByCourseIdList(List<Integer> courseIdList);
}
