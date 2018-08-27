package com.conor.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conor.spring.model.User;
import com.conor.spring.service.UserService;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserService userService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userService.findByName(name);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), true, true,
				true, true, getGrantedAuthorities(user));
	}

	// assigning temporary role to all users
	// as not currently using role-based authentication
	// can be added to in future
	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_TEMP"));
		return authorities;
	}

}
