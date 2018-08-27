package com.conor.spring.dao;

import com.conor.spring.model.User;

public interface UserDao {

	User findById(int id);

	User findByName(String name);

	void save(User user);
}
