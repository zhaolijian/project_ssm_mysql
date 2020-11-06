package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> findAll(int page, int size) throws Exception;
    Orders findById(String id) throws Exception;
}
