package com.yanty.friends.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanty.friends.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}