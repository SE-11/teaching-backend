package com.teaching.service;

import com.teaching.pojo.Teacher;

public interface TeacherService {
    int save(Teacher teacher);
    int registered(Teacher teacher);
    int getByPhone(String phone);
    boolean checkPassword(int id, String password);
    Teacher getTeacherById(Integer id);
}
