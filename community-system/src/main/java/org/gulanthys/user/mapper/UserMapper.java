package org.gulanthys.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.gulanthys.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
