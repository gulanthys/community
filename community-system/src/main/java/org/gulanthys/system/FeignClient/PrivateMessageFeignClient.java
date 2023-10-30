package org.gulanthys.system.FeignClient;


import org.gulanthys.system.entity.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "friends-server", url = "localhost:8081", path = "/")
public interface PrivateMessageFeignClient {

    @GetMapping("/hello")
    String hello();

    @PostMapping("/friends-server/systemNotice/individual")
    String systemNotice(@RequestParam("receiverId") String receiverId, @RequestParam("content") String content);

    @PostMapping("/friends-server/systemNotice/individual")
    String systemNotice(@SpringQueryMap Message message);
}
