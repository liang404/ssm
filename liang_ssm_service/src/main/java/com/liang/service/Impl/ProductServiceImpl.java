package com.liang.service.Impl;

import com.liang.dao.ProductDao;
import com.liang.domain.Product;
import com.liang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/24 16:36
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    //调用dao层方法
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(String[] d) {
        for(String ids:d){
            productDao.delete(ids);
        }
    }
}
