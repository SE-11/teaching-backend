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
}
