package com.training.restLibrary.security.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.restLibrary.exception.UsernameTakenException;
import com.training.restLibrary.security.entity.Role;
import com.training.restLibrary.security.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class UsersDAOImpl implements UsersDAO {
	EntityManager entityManager;

	public UsersDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void saveUser(User user) {
		User db_user = entityManager.find(User.class, user.getUser());
		if (db_user != null) {
			throw new UsernameTakenException(String.format("User ID (%s) already exist, Please try again..",  user.getUser())); // place holder
		}
		
		// add default role so creating new User wont require a role element
		user.addRole(new Role(user.getUser(), "ROLE_USER"));
		
		entityManager.persist(user);
	}

	@Override
	public User findUserByUserId(String user) {
		return entityManager.find(User.class, user);
	}

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> query = entityManager.createQuery("From User", User.class);
		
		return query.getResultList();
	}

	@Override
	public User updateUser(User user) {
		return entityManager.merge(user);
	}

}
