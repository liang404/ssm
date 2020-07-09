package com.liang.dao;

import com.liang.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/26 14:26
 */
public interface TravellerDao {
    @Select("select * from traveller where id in(select travellerId from order_traveller where orderId = #{ordersId})")
    List<Traveller> findByOrdersId(String ordersId);
}
