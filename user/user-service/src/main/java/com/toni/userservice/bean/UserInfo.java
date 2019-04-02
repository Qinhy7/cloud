package com.toni.userservice.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ：qinhy
 * @date ：Created in 2019/4/1 0001 15:51
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;

}
