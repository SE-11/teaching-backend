package com.teaching.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCourse {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer teacherId;
    private Integer courseId;

    public TeacherCourse(int teacherId, int courseId) {
        this.teacherId = teacherId;
        this.courseId = courseId;
    }

}
