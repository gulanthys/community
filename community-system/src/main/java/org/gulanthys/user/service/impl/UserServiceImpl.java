package org.gulanthys.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.gulanthys.user.entity.User;
import org.gulanthys.user.mapper.UserMapper;
import org.gulanthys.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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
