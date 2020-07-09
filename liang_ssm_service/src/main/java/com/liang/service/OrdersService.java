package com.liang.service;

import com.liang.domain.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/26 1:53
 */

public interface OrdersService {

    //查询所有操作
    List<Orders> findAll(int page,int size);

    Orders findById(String ordersId);
}
