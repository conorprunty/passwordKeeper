package com.conor.spring.service;

import java.util.List;

import com.conor.spring.model.Passwords;

public interface PasswordsService {

	List<Passwords> findAllPasswords(String userName);

	void savePasswords(Passwords passwords);

	void deletePasswordsById(int id);

	Passwords findById(int id);

	void updatePasswords(Passwords passwords);
}
