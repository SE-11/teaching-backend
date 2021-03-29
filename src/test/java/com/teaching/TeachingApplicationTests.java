package com.teaching;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.CourseMapper;
import com.teaching.mapper.StudentMapper;
import com.teaching.pojo.Course;
import com.teaching.pojo.Student;
import com.teaching.service.CourseService;
import com.teaching.service.StudentService;
import com.teaching.util.Util;
import com.teaching.vo.CourseTeacherVO;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TeachingApplicationTests {

//    @Autowired
//    DataSource dataSource;
//    @Test
//    void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
//        System.out.println(dataSource.getConnection());
//    }
//
//    @Autowired
//    private StudentService studentService;
//    @Test
//    public void testGetByPhone() {
//        System.out.println("----------------------------");
//        int a = studentService.getByPhone("888");
//        System.out.println(a);
//
//        boolean checkPassword = studentService.checkPassword(10, "698d51a19d8a121ce581499d7b70166");
//        System.out.println("------------check password----------------");
//        System.out.println(checkPassword);
//    }
//
//    @Autowired
//    private CourseService courseService;
//
//    @Test
//    public void testListCourseByTeacherId() {
//        System.out.println("===============================");
//        List<Course> courseList = new ArrayList<Course>();
//        courseList = courseService.listByTeacherId(5);
//        System.out.println(courseList.toString());
//    }


//    @Autowired
//    private StudentMapper studentMapper;
//    @Test
//    public void testSelect() {
//        System.out.println("----- selectAll method test ------");
//        List<Student> studentList = studentMapper.selectList(null);
//        studentList.forEach(System.out::println);
//
//        System.out.println("----- selectOne method test ------");
//        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("phone", "123");
//        if(studentMapper.selectOne(queryWrapper) == null ) {
//            System.out.println("none ----------------------------------");
//        } else {
//            System.out.println("have");
//        }
//    }
//    @Test
//    public void testInvitationCode() {
//        System.out.println("==============================");
//        for(int i = 0; i < 6; i++) {
//            System.out.println(Util.generateInvitationCode());
//        }
//    }
    @Autowired
    private CourseMapper courseMapper;
    @Test
    public void testListCourseTeacherVO() {
        System.out.println("========================");
        List<CourseTeacherVO> courseTeacherVOS = new ArrayList<>();
        courseTeacherVOS = courseMapper.courseInforList(5);
    }
}
