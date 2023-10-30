package com.yanty.friends.FeignClient;


import com.yanty.friends.entity.User;
import org.community.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Service
@Component
@FeignClient(name = "UserService", url = "localhost:8080", path = "/")
public interface UserFeignClient {

    @GetMapping("/user/test")
    String testLink();

    @GetMapping("/user/list")
    String userList(String user);

}
