package org.gulanthys.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.gulanthys.user.entity.User;
import org.gulanthys.user.mapper.UserMapper;
import org.gulanthys.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public boolean save(User entity) {
        entity.setUserId(generateUniqueUserId(entity.getId()));
        entity.setUserName(generateRandomString());
        return super.save(entity);
    }

    /**
     * 生成随机10位字符串
     *
     * @return String字符串
     */
    private String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(10);
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    /**
     * 初始userId
     *
     * @param userId
     * @return
     */
    private int generateUniqueUserId(int userId) {
        int res = userId + 100000000;
        return res;
    }
}
