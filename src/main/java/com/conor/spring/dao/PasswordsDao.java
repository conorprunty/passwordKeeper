package com.conor.spring.dao;

import java.util.List;

import com.conor.spring.model.Passwords;

public interface PasswordsDao {
	
	List<Passwords> findAllPasswords(String userName);
	
	void savePasswords(Passwords passwords);
	
	void deleteById(int id);
	
	Passwords findById(int id);
}
