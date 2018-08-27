package com.conor.spring.service;

import java.util.List;

import com.conor.spring.model.User;
 
 
public interface UserService {
     
    User findById(int id);
     
    User findByName(String name);
    
    void saveUser(User user);
 
}
