package com.yy.sxmyt.user.service;

import java.util.List;

import com.yy.sxmyt.entity.UserEntity;

public interface UserService {

	public String userLogin(String name ,String pwd);
	
	/**
	 * mybaits 分页插件分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<UserEntity> findByPage(int pageNum , int pageSize);
	
}
