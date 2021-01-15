package com.muyi.courage.user.repository;


import com.muyi.courage.user.domain.UserDO;
import com.muyi.courage.user.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

    UserPO selectByPrimaryKey(String userno);

    @Insert({
            "insert into SYS_USER (USER_NO, USER_NAME, PASSWORD, STATUS)",
            "values (#{userNo,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, " +
                    "#{password,jdbcType=VARCHAR}, #{status,jdbcType=NUMERIC})"
    })
    int insert(UserDO record);
}
