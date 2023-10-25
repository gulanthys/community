package org.gulanthys.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.mapper.UserMapper;
import org.gulanthys.system.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    public boolean save(User entity) {
        String password = entity.getPassword();
        String encode = passwordEncoder.encode(password);
        entity.setPassword(encode);
        userMapper.insert(entity);
        return true;
    }
}