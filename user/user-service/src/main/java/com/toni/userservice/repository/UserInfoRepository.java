package com.toni.userservice.repository;

import com.toni.userservice.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ：qinhy
 * @date ：Created in 2019/4/1 0001 15:55
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo getOneByOpenid(String openid);

}
