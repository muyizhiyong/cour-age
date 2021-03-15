package com.muyi.courage.auth.security.authentication;

import com.muyi.courage.auth.domain.SysUserDO;
import com.muyi.courage.auth.po.SysUserPO;
import com.muyi.courage.auth.repository.SysUserMapper;
import com.muyi.courage.auth.security.userdetails.PrivateUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service("userDetailsService")
public class LoginUserDetailsServiceImpl implements UserDetailsService {

	private final SysUserMapper sysUserMapper;

	@Autowired
	public LoginUserDetailsServiceImpl(SysUserMapper sysUserMapper) {
		this.sysUserMapper = sysUserMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.debug("[loadUserByUsername] username :"+username);
		SysUserPO sysUserPO = sysUserMapper.selectByPrimaryKey(username);

		if (sysUserPO == null) {
			throw new UsernameNotFoundException(username);
		}
		PrivateUserDetails privateUserDetails = new PrivateUserDetails();
		privateUserDetails.setPassword(sysUserPO.getPassword());
		privateUserDetails.setUsername(sysUserPO.getUserName()); //此处有区别，账号为系统表userNO
		log.debug("[loadUserByUsername] end ");
		return privateUserDetails;
	}
}
