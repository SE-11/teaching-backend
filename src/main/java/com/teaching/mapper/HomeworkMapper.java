package com.teaching.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teaching.pojo.Homework;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeworkMapper extends BaseMapper<Homework> {
}
