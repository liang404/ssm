package com.liang.service;

import com.liang.domain.Permission;
import com.liang.domain.Role;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/29 15:32
 */
public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    List<Permission> findOtherPermission(String roleId);

    Role findById(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
