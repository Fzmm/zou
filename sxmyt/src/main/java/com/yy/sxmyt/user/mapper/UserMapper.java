package com.yy.sxmyt.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.yy.sxmyt.entity.UserEntity;

public interface UserMapper {

	/**
	 * 查询用户角色
	 * @param name
	 * @param pwd
	 * @return
	 */
	public List<String> findUserRole( @Param("username")String name );
	
	/**
	 * 查询用户权限
	 * @param name
	 * @return
	 */
	public List<String> findUserPermissions( @Param("username")String name) ;
	
	/**
	 * 用户登入
	 * @param name
	 * @param pwd
	 * @return
	 */
	public UserEntity userLogin(@Param("name")String name , @Param("pwd")String pwd);
	
	/**
	 * 名字做为唯一凭证
	 * @param name
	 * @return
	 */
	public UserEntity userLoginByName(@Param("username")String name);
	
	/**
	 * list
	 * @return
	 */
	public Page<UserEntity> findByPage();
}
