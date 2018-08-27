package com.conor.spring.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.conor.spring.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public User findById(int id) {
		User user = getByKey(id);

		return user;
	}

	public User findByName(String name) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("name", name));
		User user = (User) crit.uniqueResult();

		return user;
	}

	public void save(User user) {
		persist(user);
	}

}
