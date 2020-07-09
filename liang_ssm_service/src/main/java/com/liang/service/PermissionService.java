package com.liang.service;

import com.liang.domain.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/29 16:09
 */

public interface PermissionService {
    List<Permission> findAll();

    void save(Permission permission);
}
