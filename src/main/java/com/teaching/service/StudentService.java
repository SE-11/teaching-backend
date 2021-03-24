package com.teaching.service;

import com.teaching.pojo.Student;

import java.util.List;

public interface StudentService {
    /*
    * list all student
    * */
    List<Student> listAllStudent();

    /*
    * save a student without id
    * */
    int save(Student student);

    int registered(Student student);

    /*
    * 查询 student 表中是否含有该电话号码
    * 存在则返回 id
    * 不存在返回 -1
    * */
    int getByPhone(String phone);

    boolean checkPassword(int id, String password);
}
