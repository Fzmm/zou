package com.yy.sxmyt.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.yy.sxmyt.entity.UserEntity;
import com.yy.sxmyt.user.mapper.UserMapper;
import com.yy.sxmyt.user.service.UserService;

@Service
public class UserServerImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Cacheable("user")
	public String userLogin(String name, String pwd) {
		UserEntity us = userMapper.userLoginByName(name);
		System.err.println("---------------------------------------");
		if( us == null){
			return "error";
		}
		return "TheIndex";
	}

	@Override
	public List<UserEntity> findByPage(int pageNum , int pageSize) {
		PageHelper.startPage( pageNum , pageSize );
		return userMapper.findByPage();
	}
	
	

}
