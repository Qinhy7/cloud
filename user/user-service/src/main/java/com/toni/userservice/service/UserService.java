package com.toni.userservice.service;

import com.toni.userservice.bean.UserInfo;

/**
 * @author ：qinhy
 * @date ：Created in 2019/4/1 0001 15:56
 */
public interface UserService {

    /**
     * 根据用户id获取用户信息
     * @param openid
     * @return
     */
    UserInfo getByOpenid(String openid);

}
