package com.teaching;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.teaching.mapper.StudentMapper;
import com.teaching.pojo.Student;
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
    private StudentMapper studentMapper;
    @Test
    public void testSelect() {
        System.out.println("----- selectAll method test ------");
        List<Student> studentList = studentMapper.selectList(null);
        studentList.forEach(System.out::println);

        System.out.println("----- selectOne method test ------");
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", "123");
        if(studentMapper.selectOne(queryWrapper) == null ) {
            System.out.println("none ----------------------------------");
        } else {
            System.out.println("have");
        }

    }
}
