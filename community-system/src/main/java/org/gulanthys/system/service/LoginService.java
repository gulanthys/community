package org.gulanthys.system.service;

import org.community.common.Result;
import org.gulanthys.system.entity.User;

import java.util.Map;

public interface LoginService {
    Map<?, ?> Login(User user);

    Result<?> Logout();


}
