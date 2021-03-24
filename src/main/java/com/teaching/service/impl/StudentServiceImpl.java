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
        int retId = studentMapper.insert(student);
        return retId;
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
}
