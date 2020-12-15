package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Student;
import com.example.demo.dto.User;


@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudents()
	{
	List<Student> list = (List<Student>) studentRepository.findAll();
	return list;
	}
	
	public Optional<Student> getStudentById(String id) {
		return studentRepository.findById(id);
		}
	
	
	public List<Student> findAllByStudentId(String id)
	{
	List<Student> list = (List<Student>) studentRepository.findAllByStudentId(id);
	return list;
	}
	
	public List<Student> findAllByClassName(String className)
	{
	List<Student> list = (List<Student>) studentRepository.findAllByClassName(className);
	return list;
	}
	
	
	public List<Student> findAllByStudentName(String name)
	{
	List<Student> list = (List<Student>) studentRepository.findAllByStudentName(name);
	return list;
	}

	
	public int save(Student student) 
	{
		int result=1;
		studentRepository.save(student);
		return result;
	}
	
	public int delete(String id) {
		int result=1;
		studentRepository.deleteById(id);
		return result;
	}
	
	public int update(Student student, String id) {
		int result=1;
		studentRepository.save(student);
		return result;
	}
}
