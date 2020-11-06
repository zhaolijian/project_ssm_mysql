package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IProductDao;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception {
//        在真正执行sql前，使用pageHelper完成分页操作
//        pageNum：页码值， pageSize：每页显示条数
        PageHelper.startPage(1, 5);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
