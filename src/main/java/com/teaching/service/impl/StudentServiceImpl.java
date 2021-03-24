package com.teaching.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.StudentMapper;
import com.teaching.pojo.Student;
import com.teaching.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> listAllStudent() {
        List<Student> studentList = studentMapper.selectList(null);
        return studentList;
    }

    @Override
    public int save(Student student) {
        int id = studentMapper.insert(student);
        return id;
    }

    @Override
    public int getByPhone(String phone) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        // int retId;
        Student student = studentMapper.selectOne(queryWrapper);
        if(student == null) {
            return -1;
        } else {
            // System.out.println(student.toString());
            return student.getStudentId();
        }
    }

    @Override
    public int registered(Student student) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", student.getPhone());

        // 无记录，可插入该记录
        int retId;
        if(studentMapper.selectOne(queryWrapper) == null) {
            studentMapper.insert(student);
            retId = student.getStudentId();
        } else {
            retId = -1;
        }
        return retId;
    }

    // 用户密码校验
    @Override
    public boolean checkPassword(int id, String password) {
        Student student = studentMapper.selectById(id);
        if(student.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
