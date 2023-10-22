package org.gulanthys.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.mapper.UserMapper;
import org.gulanthys.system.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}