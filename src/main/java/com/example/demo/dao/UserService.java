package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.User;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers()
	{
	List<User> list = (List<User>) userRepository.findAll();
	return list;
	}
	
	public List<User> getAllUsersbyId(String id)
	{
	List<User> list = (List<User>) userRepository.findAllById(id);
	return list;
	}
	
	
	public List<User> FindByUserName(String name)
	{
	List<User> list = (List<User>) userRepository.findAllByUserName(name);
	return list;
	}
	
	public Optional<User> getUserById(String id) {
		return userRepository.findById(id);
		}
	
	public int save(User user) 
	{	
		int result=1;
		userRepository.save(user);
		return result;
	}
	
	public int delete(String id) {
		int result=1;
		userRepository.deleteById(id);
		return result;
	}
	
	public int update(User user, String id) {
		int result=1;
		userRepository.save(user);
		return result;
	}
	
}
