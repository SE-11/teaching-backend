package com.teaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.AnnounceMapper;
import com.teaching.mapper.CourseMapper;
import com.teaching.pojo.Announce;
import com.teaching.pojo.Course;
import com.teaching.service.CourseService;
import com.teaching.util.Util;
import com.teaching.vo.CourseTeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private AnnounceMapper announceMapper;

    // 插入成功 返回 id
    // 插入时创建 6位 唯一课堂码
    @Override
    public int save(Course course) {
        // generate invitation code
        String invitationCode = Util.generateInvitationCode();
        // 查询 course 表中是否有相同 invitationCode 的记录
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("invitation_code", invitationCode);
        while (courseMapper.selectOne(queryWrapper) != null) {
            // 有相同的 invitationCode
            invitationCode = Util.generateInvitationCode();
            queryWrapper.eq("invitation_code", invitationCode);
        }
        course.setInvitationCode(invitationCode);
        courseMapper.insert(course);
        int id = course.getCourseId();
        return id;
    }

    @Override
    public Course getById(Integer id) {
        Course course = courseMapper.selectById(id);
        return course;
    }

    /*
    * list 对应教师id下的所有课程信息（CourseTeacherVO）
    *
    * */
    @Override
    public List<CourseTeacherVO> listByTeacherId(Integer teacherId) {
        // 将教师的相关信息也加入返回列表中
//        List<Course> courseList = new ArrayList<Course>();
//        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>();
//        queryWrapper.eq("teacher_id", id);
//        courseList = courseMapper.selectList(queryWrapper);
//        return courseList;
        List<CourseTeacherVO> courseTeacherVOS = new ArrayList<>();
        courseTeacherVOS = courseMapper.courseInforList(teacherId);
        return courseTeacherVOS;
    }

    @Override
    public CourseTeacherVO getCourseInfoByCourseId(Integer courseId) {
        CourseTeacherVO courseTeacherVO = new CourseTeacherVO();
        courseTeacherVO = courseMapper.selectCourseInfoByCourseId(courseId);
        return courseTeacherVO;
    }

    // return -1 表示课程码不匹配-----无记录
    @Override
    public int getIdByInvitationCode(String invitationCode) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("invitation_code", invitationCode);

        Course course = courseMapper.selectOne(queryWrapper);
        if(course != null) {
            return course.getCourseId();
        } else {
            return -1;
        }
    }

    @Override
    public List<CourseTeacherVO> listCourseInfoByCourseIdList(List<Integer> courseIdList) {
        List<CourseTeacherVO> courseTeacherVOS = new ArrayList<>();
        courseIdList.forEach(item -> {
            courseTeacherVOS.add(courseMapper.selectCourseInfoByCourseId(item));
        });
        return courseTeacherVOS;
    }

    @Override
    public int saveAnnounce(Announce announce) {
        announceMapper.insert(announce);
        return announce.getId();
    }

    @Override
    public Announce getAnnById(Integer id) {
        Announce announce = announceMapper.selectById(id);
        return announce;
    }

    @Override
    public List<Announce> listAnnByCourseId(Integer id) {
        QueryWrapper<Announce> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        return announceMapper.selectList(queryWrapper);
    }
}
