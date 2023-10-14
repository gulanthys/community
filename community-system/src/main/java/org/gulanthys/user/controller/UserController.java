package org.gulanthys.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.community.common.Constants;
import org.community.common.Result;
import org.gulanthys.user.entity.User;
import org.gulanthys.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 账户注册
     *
     * @param user 用户信息
     * @return res
     */
    @PostMapping("/register")
    public Result<?> UserRegister(@RequestBody User user) {
        log.info("user:{}", user);
        boolean res = userService.insert(user);
        if (!res) {
            return Result.buildResult(Constants.Status.EXIT);
        }
        return Result.buildResult(Constants.Status.OK);
    }

    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return Result.buildResult(Constants.Status.OK, "用户信息", user);
    }

    /**
     * 用户查询
     *
     * @param pageNo   页码
     * @param pageSize 大小
     * @return 用户信息
     */
    @GetMapping("/list")
    public Result<?> Search(User user,
                            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<User> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.isNull(user.getUserName())) {
            queryWrapper.eq(User::getUserName, user.getUserName());
        }
        if (Objects.isNull(user.getBirth())) {
            queryWrapper.eq(User::getBirth, user.getBirth());
        }
        if (Objects.isNull(user.getEmail())) {
            queryWrapper.eq(User::getEmail, user.getEmail());
        }
        if (Objects.isNull(user.getUid())) {
            queryWrapper.eq(User::getUid, user.getUid());
        }
        if (Objects.isNull(user.getPhone())) {
            queryWrapper.eq(User::getPhone, user.getPhone());
        }
        if (Objects.isNull(user.getIdCard())) {
            queryWrapper.eq(User::getIdCard, user.getIdCard());
        }
        if (Objects.isNull(user.getPower())) {
            queryWrapper.eq(User::getPower, user.getPower());
        }
        if (Objects.isNull(user.getStatus())) {
            queryWrapper.eq(User::getStatus, user.getStatus());
        }
        if (Objects.isNull(user.getSex())) {
            queryWrapper.eq(User::getSex, user.getSex());
        }
        IPage<User> Data = userService.page(page, queryWrapper);
        return Result.buildResult(Constants.Status.OK, "条件查询-用户信息", Data);
    }

}
