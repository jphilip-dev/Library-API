package com.phils.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phils.library.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
