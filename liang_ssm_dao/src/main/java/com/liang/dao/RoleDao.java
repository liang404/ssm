package com.liang.dao;

import com.liang.domain.Permission;
import com.liang.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/26 22:08
 */
@Repository
public interface RoleDao {
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.liang.dao.PermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(id,roleName,roleDesc) values(#{id},#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(String roleId);

    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);

    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
