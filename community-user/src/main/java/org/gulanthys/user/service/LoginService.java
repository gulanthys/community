package org.gulanthys.user.service;

import org.community.common.Result;
import org.gulanthys.user.entity.User;

public interface LoginService {
    Result<?> Login(User user);

    Result<?> Logout();
}
