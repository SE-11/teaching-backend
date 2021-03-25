package com.teaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.TeacherMapper;
import com.teaching.pojo.Teacher;
import com.teaching.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public int save(Teacher teacher) {
        int retId = teacherMapper.insert(teacher);
        return retId;
    }

    @Override
    public int getByPhone(String phone) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("phone", phone);
        Teacher teacher = teacherMapper.selectOne(teacherQueryWrapper);
        if(teacher == null) {
            return -1;
        } else {
            return teacher.getTeacherId();
        }
    }
    /*
    * 成功返回 teacherId
    * 失败返回 -1
    * */
    @Override
    public int registered(Teacher teacher) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", teacher.getPhone());

        int retId;
        if(teacherMapper.selectOne(queryWrapper) == null) {
            teacherMapper.insert(teacher);
            retId = teacher.getTeacherId();
        } else {
            retId = -1;
        }
        return retId;
    }

    @Override
    public boolean checkPassword(int id, String password) {
        Teacher teacher = teacherMapper.selectById(id);
        if(teacher.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Teacher getTeacherById(Integer id) {
        Teacher teacher = teacherMapper.selectById(id);
        return teacher;
    }
}
