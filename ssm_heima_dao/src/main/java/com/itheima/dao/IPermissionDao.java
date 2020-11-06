package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
//    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findPermissionByRoleId(String id) throws Exception;

//    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

//    @Insert("insert into permission(permissionName, url) values(#{permissionName}, #{url})")
    void save(Permission permission) throws Exception;


//    @Delete("delete from role_permission where permissionId = #{permissionId}")
    void deleteFromRole_Permission(@Param("permissionId") String permissionId) throws Exception;

//    @Delete("delete from permission where id = #{permissionId}")
    void deleteById(@Param("permissionId") String permissionId) throws Exception;

//    @Select("select * from permission where id = #{permissionId}")
    Permission findById(@Param("permissionId") String permissionId) throws Exception;
}
