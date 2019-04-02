package com.toni.userservice.service.impl;

import com.toni.userservice.bean.UserInfo;
import com.toni.userservice.repository.UserInfoRepository;
import com.toni.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：qinhy
 * @date ：Created in 2019/4/1 0001 15:57
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo getByOpenid(String openid) {
        return userInfoRepository.getOneByOpenid(openid);
    }
}
