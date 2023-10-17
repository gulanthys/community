package org.gulanthys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.gulanthys.system.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
