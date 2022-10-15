package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.EmployeeException;
import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository er;

	// get all employees
	public Iterable<Employee> findEmployees() {
		return er.findAll();
	}

	// get employee by employee id
	public void findEmployeesByEid(String eid) {
		Optional<Employee> emp = er.findByEid(eid);
		if (emp.isEmpty()) {
			throw new EmployeeException("employee: " + eid + " not found");
		}
	}

	public void deleteEmployeeByEid(String eid) {
		Optional<Employee> emp = er.findByEid(eid);
		if (emp.get().getProjects().isEmpty()) {
			er.delete(emp.get());
		} else {
			throw new EmployeeException("employee eid:" + eid + " can't be deleted. He/she is working on a project.");
		}
	}

	public void save(Employee e) {
		try {
			er.save(e);
		} catch (DataIntegrityViolationException ex) {
			throw new EmployeeException("employee: " + e.getEid() + " already exists");
		}
	}
}
