package com.toni.userservice.controller;

import com.toni.userservice.bean.UserInfo;
import com.toni.userservice.constant.CookieConstant;
import com.toni.userservice.constant.RedisConstant;
import com.toni.userservice.enums.ResultEnum;
import com.toni.userservice.enums.UserRoleEnum;
import com.toni.userservice.service.UserService;
import com.toni.userservice.utils.CookieUtil;
import com.toni.userservice.utils.ResultVOUtil;
import com.toni.userservice.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ：qinhy
 * @date ：Created in 2019/4/1 0001 18:22
 */
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response){
        // 查询数据库，
        UserInfo userInfo = userService.getByOpenid(openid);
        if(userInfo == null){
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        // 比较权限
        if(!userInfo.getRole().equals(UserRoleEnum.BUYER.getCode())){
            return ResultVOUtil.error(ResultEnum.USER_ROLE_ERROR);
        }
        // 在cookie中写入openid
        CookieUtil.set(response, CookieConstant.token, openid, CookieConstant.expire);
        // 返回结果
        return ResultVOUtil.success(null);
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid,
                           HttpServletResponse response,
                           HttpServletRequest request){
        // 每次登陆先判断redis中是否存在
        Cookie cookie = CookieUtil.get(request, CookieConstant.token);
        if(cookie != null && !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.token, cookie.getValue())))){
            return ResultVOUtil.success("已经登陆");
        }

        // 查询数据库，
        UserInfo userInfo = userService.getByOpenid(openid);
        if(userInfo == null){
            return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST);
        }
        // 比较权限
        if(!userInfo.getRole().equals(UserRoleEnum.SELL.getCode())){
            return ResultVOUtil.error(ResultEnum.USER_ROLE_ERROR);
        }

        // cookie里设置token=UUID, redis设置key=UUID, value=xyz
        // redis写入
        String uuid = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.token, uuid),
                openid,
                expire,
                TimeUnit.SECONDS);
        // cookie写入
        CookieUtil.set(response, CookieConstant.token, uuid, CookieConstant.expire);
        // 返回结果
        return ResultVOUtil.success(null);
    }

}
