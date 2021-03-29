package com.teaching.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class Course {
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;
    private String courseName;
    private Integer teacherId;
    private String invitationCode;
    private String courseStartTime;
    private String courseEndTime;
    private String cover;
}
