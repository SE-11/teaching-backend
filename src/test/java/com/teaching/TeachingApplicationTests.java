package com.teaching;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.StudentMapper;
import com.teaching.pojo.Student;
import com.teaching.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class TeachingApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Autowired
    private StudentService studentService;
    @Test
    public void testGetByPhone() {
        System.out.println("----------------------------");
        int a = studentService.getByPhone("888");
        System.out.println(a);

        boolean checkPassword = studentService.checkPassword(10, "698d51a19d8a121ce581499d7b70166");
        System.out.println("------------check password----------------");
        System.out.println(checkPassword);
    }

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
}
