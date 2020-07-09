package com.liang.dao;

import com.liang.domain.Member;
import com.liang.domain.Orders;
import com.liang.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/26 1:56
 */
@Repository
public interface OrdersDao {
    //查询所有方法
    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.liang.dao.ProductDao.findById"))
    })
    List<Orders> findAll();

    @Select("select * from orders where id = #{ordersId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,one = @One(select = "com.liang.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.liang.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType = List.class,many = @Many(select = "com.liang.dao.TravellerDao.findByOrdersId"))
    })
    Orders findById(String ordersId);
}
