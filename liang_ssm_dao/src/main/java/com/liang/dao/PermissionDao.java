package com.liang.dao;

import com.liang.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/29 11:30
 */
@Repository
public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{RoleId})")
    List<Permission> findByRoleId(String RoleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(id,permissionName,url) values(#{id},#{permissionName},#{url})")
    void save(Permission permission);
}
