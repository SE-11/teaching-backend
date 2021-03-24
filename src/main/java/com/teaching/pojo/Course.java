package com.teaching.pojo;

import lombok.Data;

@Data
public class Course {
    private Integer courseId;
    private String courseName;
    private Integer teacherId;
    private Integer invitationCode;
    private Data courseStartTime;
    private Data courseEndTime;
}
