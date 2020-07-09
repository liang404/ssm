package com.liang.service;

import com.liang.domain.Product;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/24 16:34
 */
public interface ProductService {

    List<Product> findAll() throws Exception;

    void save(Product product);

    void delete(String[] d);
}
