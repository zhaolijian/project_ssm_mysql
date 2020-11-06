package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;

    void deleteById(String permissionId) throws Exception;

    Permission findById(String permissionId) throws Exception;
}
