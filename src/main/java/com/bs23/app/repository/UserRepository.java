package com.bs23.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bs23.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
