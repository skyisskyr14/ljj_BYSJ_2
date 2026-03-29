package com.cloudyearbook.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloudyearbook.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
