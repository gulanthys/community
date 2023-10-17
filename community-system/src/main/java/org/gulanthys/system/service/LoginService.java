package org.gulanthys.system.service;

import org.community.common.Result;
import org.gulanthys.system.entity.User;

public interface LoginService {
    Result<?> Login(User user);

    Result<?> Logout();
}
