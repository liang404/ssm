package com.liang.service.Impl;

import com.github.pagehelper.PageHelper;
import com.liang.dao.OrdersDao;
import com.liang.domain.Orders;
import com.liang.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/26 1:55
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
