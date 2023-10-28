package com.yanty.friends.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanty.friends.entity.PrivateMessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrivateMessageMapper extends BaseMapper<PrivateMessage> {

    /**
     * 批量插入
     */
    int insertBatch(List<PrivateMessage> list);

    int createTable();

}
