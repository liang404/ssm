package com.liang.dao;

import com.liang.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author liang
 * @create 2020/2/26 14:13
 */
@Repository
public interface MemberDao {

    @Select("select * from member where id = #{memberId}")
    Member findById(String memberId);
}
