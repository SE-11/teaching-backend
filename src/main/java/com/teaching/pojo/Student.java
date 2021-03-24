package com.teaching.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId(value = "student_id", type = IdType.AUTO)
    private Integer studentId;
    private String studentName;
    private String email;
    private String password;
    private String studentNum;
    private String phone;
    private String avatar;
    private String college;
    private String university;
}