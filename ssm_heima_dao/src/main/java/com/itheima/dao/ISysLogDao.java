package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISysLogDao {
//    @Insert("insert into log(visitTime, username, ip, url, executionTime, method) values (" +
//            "#{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method})")
    void save(SysLog sysLog) throws Exception;


//    @Select("select * from log")
    List<SysLog> findAll() throws Exception;
}
