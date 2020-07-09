package com.liang.dao;

import com.liang.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liang
 * @create 2020/2/24 16:29
 */
@Repository
public interface ProductDao {
    /**
     * 根据id查询操作
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id);

    /**
     * 查询所有产品信息操作
     */
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    @Insert("insert into product(id,productNum,productName,cityName,DepartureTime,productPrice,productDesc,productStatus)values(#{id},#{productNum},#{productName},#{cityName},#{DepartureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    @Delete("delete from product where id = #{ids}")
    void delete(String ids);
}
