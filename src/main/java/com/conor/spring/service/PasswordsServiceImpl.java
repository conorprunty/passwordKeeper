package com.conor.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conor.spring.dao.PasswordsDao;
import com.conor.spring.model.Passwords;

@Service("PasswordsService")
@Transactional
public class PasswordsServiceImpl implements PasswordsService {

	@Autowired
	private PasswordsDao dao;

	@Override
	public List<Passwords> findAllPasswords() {
		return dao.findAllPasswords();
	}

	@Override
	public void savePasswords(Passwords passwords) {
		dao.savePasswords(passwords);
	}
	
	@Override
	public void deletePasswordsById(int id) {
		dao.deleteById(id);
	}
}
