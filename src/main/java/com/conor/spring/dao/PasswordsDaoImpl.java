package com.conor.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.conor.spring.model.Passwords;

@Repository("passwordsDao")
public class PasswordsDaoImpl extends AbstractDao<Integer, Passwords> implements PasswordsDao {

//	public List<Teams> findAllWorldCupTeams() {
//		Criteria criteria = createEntityCriteria().add(Restrictions.eq("teamType", "worldcup"))
//				.addOrder(Order.asc("name"));
//		return (List<Teams>) criteria.list();
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passwords> findAllPasswords() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("system"));
		return criteria.list();
	}
	
	@Override
	public void savePasswords(Passwords passwords) {
		persist(passwords);
	}
	
	@Override
	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Passwords passwords = (Passwords) crit.uniqueResult();
		delete(passwords);
	}
}
