package com.service;

import java.util.List;

import com.dao.UserMapper;
import com.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.model.Pagination;
import com.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao  dao ;

	@Autowired
	private UserMapper userMapper;

	public User checkUser(User user) {
		return dao.checkUser(user);
	}

	public List<User> queryAllUsers() {
		return dao.queryAllUsers();
	}
	
	public List<User> queryByWhere(User user) {
		return  userMapper.queryByWhere(user);
	}

	public int queryAllUsersTotal() {
		return dao.queryAllUsersTotal();
	}

	public List<Role> getAllRoleTypes() {
		return dao.getAllRoleTypes();
	}

	public void insertUser(User user) {
		userMapper.insertSelective(user);
	}

	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
	}

	public void delUserById(Integer id){
		userMapper.deleteByPrimaryKey(id);
	}

	public void delUserWithIds(List<String> ids) {
		userMapper.delUserWithIds(ids);
	}
}
