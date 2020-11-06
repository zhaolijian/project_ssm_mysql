package com.itheima.service.impl;

import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    @Override
    public void deleteById(String permissionId) throws Exception {
        permissionDao.deleteFromRole_Permission(permissionId);
        permissionDao.deleteById(permissionId);
    }

    @Override
    public Permission findById(String permissionId) throws Exception {
        return permissionDao.findById(permissionId);
    }
}
