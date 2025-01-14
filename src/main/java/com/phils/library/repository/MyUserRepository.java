package com.phils.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phils.library.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer>{
	
	@Query("SELECT u FROM MyUser u LEFT JOIN FETCH u.roles WHERE u.username = :username")
	Optional<MyUser> findByUsernameWithRoles(@Param("username") String username);

}
