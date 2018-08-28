package com.conor.spring.service;

import java.util.List;

import com.conor.spring.model.Passwords;

public interface PasswordsService {

	List<Passwords> findAllPasswords(); 
	
	void savePasswords(Passwords passwords);
	
	void deletePasswordsById(int id);
}
