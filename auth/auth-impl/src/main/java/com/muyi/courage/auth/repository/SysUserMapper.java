package com.muyi.courage.auth.repository;


import com.muyi.courage.auth.domain.SysUserDO;
import com.muyi.courage.auth.po.SysUserPO;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface SysUserMapper {

	int selectCountByUserNo(String userNo);

	SysUserPO selectByPrimaryKey(String username);
}
