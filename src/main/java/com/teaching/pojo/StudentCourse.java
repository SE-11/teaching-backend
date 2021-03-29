package com.teaching.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class StudentCourse {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer StudentId;
    private Integer CourseId;
}
