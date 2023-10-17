package org.gulanthys.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.mapper.UserMapper;
import org.gulanthys.system.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 添加用户信息，密码加密
     *
     * @param user 用户信息
     * @return res
     */
    public boolean insert(User user) {
        //对密码进行编码，生成盐
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        boolean res = save(user);
        return res;
    }
}
