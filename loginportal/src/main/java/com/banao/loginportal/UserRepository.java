package com.banao.loginportal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findByEmail(String email);
	public List<User> findByFirstName(String firstName);
	
}