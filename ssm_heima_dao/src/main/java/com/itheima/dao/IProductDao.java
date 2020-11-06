package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductDao {
//    根据id查询
//    @Select("select * from product where id = #{id}")
Product findById(String id) throws Exception;

//    查询所有
//    @Select("select * from product")
List<Product> findAll() throws Exception;

//    添加数据
//    @Insert("insert into product(productNum, productName, cityName, departureTime, productPrice, productDesc, productStatus) " +
//            "values(#{productNum}, #{productName}, #{cityName}, #{departureTime}, #{productPrice}, #{productDesc}, #{productStatus})")
void save(Product product);
}
