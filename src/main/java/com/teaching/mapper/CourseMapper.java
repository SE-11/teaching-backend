package com.teaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teaching.pojo.Course;
import com.teaching.vo.CourseTeacherVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    // 自定义映射 CourseTeacherVO
    @Select("SELECT " +
            "c.*," +
            "t.avatar," +
            "t.university," +
            "t.teacher_name " +
            "FROM " +
            "course c," +
            "teacher t " +
            "WHERE " +
            "c.teacher_id = t.teacher_id and t.teacher_id = #{id};")
    List<CourseTeacherVO> courseInforList(Integer id);

    // get by course id
    // return one record
    @Select("SELECT * FROM course c, teacher t WHERE c.teacher_id = t.teacher_id AND c.course_id = #{id};")
    CourseTeacherVO selectCourseInfoByCourseId(Integer id);
}
