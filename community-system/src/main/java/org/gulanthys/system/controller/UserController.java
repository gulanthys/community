package org.gulanthys.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.community.common.Constants;
import org.community.common.Result;
import org.gulanthys.system.entity.User;
import org.gulanthys.system.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PreAuthorize("hasAuthority('sys:user:list')")
    @GetMapping("/list")
    public Result<?> PageListSearch(User user, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, user.getUid());
        IPage<User> page = new Page<>(pageNo, pageSize);
        IPage<User> Data = userService.page(page, queryWrapper);
        return Result.buildResult(Constants.Status.OK, "查询成功", Data);
    }

}
