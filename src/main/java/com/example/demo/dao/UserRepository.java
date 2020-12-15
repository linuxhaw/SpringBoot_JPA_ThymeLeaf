package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.User;

@Repository
public interface UserRepository extends CrudRepository <User, String> {
	@Query(value = "SELECT * FROM user u WHERE u.NAME = ?1", nativeQuery = true)
	List<User> findAllByUserName(String name);

	@Query(value = "SELECT * FROM user u WHERE u.id = ?1", nativeQuery = true)
	List<User> findAllById(String id);

}
