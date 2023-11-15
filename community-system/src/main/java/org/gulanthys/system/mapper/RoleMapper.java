package org.gulanthys.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.gulanthys.system.entity.Role;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<String> selectRoleByUid(Serializable Uid);

    List<String> selectRoleByPath(Serializable path);
}
