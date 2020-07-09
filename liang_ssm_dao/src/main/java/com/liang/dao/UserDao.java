package com.liang.dao;

import com.liang.domain.Role;
import com.liang.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/26 17:15
 */
@Repository
public interface UserDao {
    @Select("select * from users where username = #{userName}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum" ,column = "phoneNum"),
            @Result(property = "status" ,column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.liang.dao.RoleDao.findByUserId"))
    })
    UserInfo findByUsername(String userName);


    @Select("select * from users")
    List<UserInfo> findAll();


    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);

    @Select("select * from users where id= #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum" ,column = "phoneNum"),
            @Result(property = "status" ,column = "status"),
            @Result(property = "roles",column = "id",javaType = List.class,many = @Many(select = "com.liang.dao.RoleDao.findByUserId"))
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in(select roleId from users_role where userId = #{userId}) ")
    List<Role> findOtherRole(String userId);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
