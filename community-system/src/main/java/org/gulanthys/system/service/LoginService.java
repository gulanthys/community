package org.gulanthys.system.service;

import org.community.common.Result;

import java.util.Map;

public interface LoginService {
    Map<?, ?> Login(String userName, String password);

    Result<?> Logout();


}
