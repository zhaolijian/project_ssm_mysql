package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
//    根据用户id查询所有对应的角色
//    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
//    @Results({
//            @Result(id = true, property = "id", column = "id"),
//            @Result(property = "roleName", column = "roleName"),
//            @Result(property = "roleDesc", column = "roleDesc"),
//            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionByRoleId"))
//    })

List<Role> findRoleByUserId(String userId) throws Exception;

//@Select("select * from role")
List<Role> findAll() throws Exception;

//@Insert("insert into role(roleName, roleDesc) values (#{roleName}, #{roleDesc})")
void save(Role role) throws Exception;


//@Select("select * from role where id = #{roleId}")
//@Results({
//        @Result(id = true, property = "id", column = "id"),
//        @Result(property = "roleName", column = "roleName"),
//        @Result(property = "roleDesc", column = "roleDesc"),
//        @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionByRoleId"))
//})
Role findById(String roleId) throws Exception;

//@Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
List<Permission> findOtherPermissions(String roleId) throws Exception;

//@Insert("insert into role_permission(permissionId, roleId) values (#{permissionId}, #{roleId})")
void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

//        从user_role表中删除
//@Delete("delete from users_role where roleId = #{roleId}")
void deleteFromUser_RoleByRoleId(@Param("roleId") String roleId);

//        从role_permission表中删除
//@Delete("delete from role_permission where roleId = #{roleId}")
void deleteFromRole_PermissionByRoleId(@Param("roleId") String roleId);

//        从role表中删除
//    @Delete("delete from role where id = #{roleId}")
void deleteRoleById(@Param("roleId") String roleId);
}
