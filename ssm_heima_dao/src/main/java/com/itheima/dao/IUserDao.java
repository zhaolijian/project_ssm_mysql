package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface IUserDao {

//    @Select("select * from users where username=#{username}")
//    @Results({
//            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "username", property = "username"),
//            @Result(column = "password", property = "password"),
//            @Result(column = "email", property = "email"),
//            @Result(column = "phoneNum", property = "phoneNum"),
//            @Result(column = "status", property = "status"),
//            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId"))
//    })
    UserInfo findByUsername(String username) throws Exception;


//    @Select("select * from users")
    List<UserInfo> findAll();

//    @Insert("insert into users(email, username, password, phoneNum, status) values(#{email}, #{username}, #{password}, #{phoneNum}, #{status})")
    void save(UserInfo userInfo);

//    @Select("select * from users where id = #{id}")
//    @Results({
//            @Result(id = true, column = "id", property = "id"),
//            @Result(column = "username", property = "username"),
//            @Result(column = "password", property = "password"),
//            @Result(column = "email", property = "email"),
//            @Result(column = "phoneNum", property = "phoneNum"),
//            @Result(column = "status", property = "status"),
//            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId"))
//    })
    UserInfo findById(String id);

//    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(String userId);

//    @Insert("insert into users_role(userId, roleId) values (#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}