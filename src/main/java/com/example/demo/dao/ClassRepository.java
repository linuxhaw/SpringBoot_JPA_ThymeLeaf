package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Class;

@Repository
public interface ClassRepository extends CrudRepository<Class,String>{

}
