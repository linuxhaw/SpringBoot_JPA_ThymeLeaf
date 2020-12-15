package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Class;

@Service
public class ClassService {
	
	@Autowired
	ClassRepository classRepository;
	
	public List<Class> getAllClasses()
	{
	List<Class> list = (List<Class>) classRepository.findAll();
	return list;
	}
	
	public Optional<Class> getUserById(String id) {
		return classRepository.findById(id);
		}
	
	public int save(Class classes) 
	{
		int result=1;
		classRepository.save(classes);
		return result;
	}
	
	
}
