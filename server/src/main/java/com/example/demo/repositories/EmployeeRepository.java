package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	
	// find employee by using employee id
	public Optional<Employee> findByEid(String eid); // select * from employees where eid = "?"

}
