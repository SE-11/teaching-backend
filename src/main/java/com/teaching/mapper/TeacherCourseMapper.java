package com.teaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teaching.pojo.TeacherCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherCourseMapper extends BaseMapper<TeacherCourse> {
    @Select("SELECT course_id FROM teacher_course WHERE teacher_id = #{teacherId};")
    List<Integer> listAllJoinCourse(Integer teacherId);
}
