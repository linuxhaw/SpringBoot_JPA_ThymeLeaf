package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Student;
import com.example.demo.dto.User;

@Repository
public interface StudentRepository extends CrudRepository<Student,String>{
	@Query(value = "SELECT * FROM student s WHERE s.student_name = ?1", nativeQuery = true)
	List<Student> findAllByStudentName(String name);

	@Query(value = "SELECT * FROM student s WHERE s.student_id = ?1", nativeQuery = true)
	List<Student> findAllByStudentId(String id);
	
	@Query(value = "SELECT * FROM student s WHERE s.class_name = ?1", nativeQuery = true)
	List<Student> findAllByClassName(String className);

	
	
}
