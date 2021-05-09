package com.teaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teaching.pojo.StudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {
    @Select("SELECT course_id FROM student_course WHERE student_id = #{studentId};")
    List<Integer> listAllJoinCourse(Integer studentId);
}
