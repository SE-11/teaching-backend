package com.teaching.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Teacher {
    @TableId(value = "teacher_id", type = IdType.AUTO)
    private Integer teacherId;
    private String teacherName;
    private String email;
    private String avatar;
    private String password;
    private String university;
    private String phone;
    // add
    private String cover;
    private String description;
    private String address;
}
