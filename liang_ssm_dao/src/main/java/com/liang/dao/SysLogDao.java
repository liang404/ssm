package com.liang.dao;

import com.liang.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author liang
 * @create 2020/3/1 23:28
 */
@Repository
public interface SysLogDao {
    @Insert("insert into syslog(id,visitTime,username,ip,url,executionTime,method) values(#{id},#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);
}
