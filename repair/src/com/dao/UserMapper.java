package com.dao;

import java.util.List;

import com.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    int delUserWithIds(List<String> ids);

	List<User> queryByWhere(User user);
}