package com.teaching.pojo;

import lombok.Data;

// 教师-课程表
@Data
public class Teach {
    private Integer teachId;
    private Integer teacherId;
    private Integer courseId;
}
