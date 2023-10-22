package org.gulanthys.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.gulanthys.system.entity.LoginUser;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.mapper.MenuMapper;
import org.gulanthys.system.mapper.UserMapper;
import org.gulanthys.system.service.LoginUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, username)
                .or().eq(User::getUid, username)
                .or().eq(User::getPhone, username)
                .or().eq(User::getIdCard, username);
        User user = userMapper.selectOne(queryWrapper);
        //如果不存在，抛出异常
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("登录信息错误");
        }
        //TODO (授权，即查询用户具有哪些权限)查询对应的用户信息
        List<String> list = menuMapper.selectPermsByUserId(user.getUid());
        //存在，将信息封装进UserDetails种
        return new LoginUser(user, list);
    }
}
