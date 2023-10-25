package com.yanty.friends.FeignClient;


import com.yanty.friends.entity.User;
import org.community.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Component
@FeignClient("document-service")
public interface DocymentFeignClient {

    @GetMapping("/hello")
    String hello();

}
