package com.yanty.friends;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages="com.yanty.friends.FeignClient")
@SpringBootApplication
public class FriendsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendsApplication.class, args);
    }

}
