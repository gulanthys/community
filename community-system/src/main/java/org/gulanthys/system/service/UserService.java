package org.gulanthys.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.gulanthys.system.entity.User;


public interface UserService extends IService<User> {
    public boolean insert(User user);
}
