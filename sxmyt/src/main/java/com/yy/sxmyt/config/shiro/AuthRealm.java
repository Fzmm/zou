package com.yy.sxmyt.config.shiro;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yy.sxmyt.entity.UserEntity;
import com.yy.sxmyt.user.mapper.UserMapper;

public class AuthRealm extends AuthorizingRealm{

	@Autowired
	private UserMapper userMapper;
	
	private Logger logger = Logger.getLogger(AuthRealm.class);
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info(" Get user role and Permissions ---");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String currentLoginName = (String)principals.getPrimaryPrincipal();
		//获取用户角色
		List<String> userRoles = userMapper.findUserRole(currentLoginName);
        //获取用户权限
        List<String> userPermissions = userMapper.findUserPermissions(currentLoginName);
		
        authorizationInfo.addRoles(userRoles);
        authorizationInfo.addStringPermissions(userPermissions);
		return authorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		logger.info(" into  CredentialsMatcher   ---");
		UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
		String username = utoken.getUsername();
		UserEntity user = userMapper.userLoginByName(username);
		return new SimpleAuthenticationInfo(user, user.getUser_password(),this.getClass().getName());
		//放入shiro.调用CredentialsMatcher检验密码
	}

}
