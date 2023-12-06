package org.yian.chicksiteblog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yian.common.CommonResult;


/**
 * 用户控制器
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @GetMapping(value = "/login")
    public CommonResult login(String username, String password) {
        return CommonResult.successNoData("登录成功");
    }
}
