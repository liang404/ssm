package com.liang.service;

import com.liang.domain.Role;
import com.liang.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/26 17:08
 */
public interface UserService extends UserDetailsService {

    List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRole(String userId);

    void addRoleToUser(String userId, String[] roleIds);
}
