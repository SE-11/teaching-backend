package com.teaching.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Homework {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer courseId;
    private String title;
    private String startTime;
    private String endTime;
    private String type;
    private String oldFilename;
    private String newFilename;
    private String path;

    public Homework(Integer courseId, String title, String startTime, String endTime, String type, String oldFilename, String newFilename, String path) {
        this.courseId = courseId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.oldFilename = oldFilename;
        this.newFilename = newFilename;
        this.path = path;
    }
}
