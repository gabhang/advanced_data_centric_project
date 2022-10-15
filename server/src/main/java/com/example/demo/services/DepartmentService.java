package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.DepartmentException;
import com.example.demo.models.Department;
import com.example.demo.models.Employee;
import com.example.demo.repositories.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository dr;

	// get all departments
	public Iterable<Department> findDepartments() {
		return dr.findAll();
	}

	// update department
	public void updateDept(String did, Department d) {
		// find if department id exists
		Optional<Department> dept = dr.findByDid(did);
		// if does not exists, throw exception, else, save and update
		if (dept.isEmpty()) {
			throw new DepartmentException("department did:" + did + " doesn't exists");
		} else {
			dept.get().setName(d.getName());
			dept.get().setLocation(d.getLocation());
			dr.save(dept.get());
		}
	}

	// get total salary
	public double sumSalary(String did) {
		// declaration
		double sum = 0.0;
		// find if department id exists
		Optional<Department> dept = dr.findByDid(did);
		// if does not exists, throw exception, else, loop through all employees in that department and sum their salary
		if (dept.isEmpty()) {
			throw new DepartmentException("department: " + did + " not found");
		} else {
			for (Employee emp : dept.get().getEmployees()) {
				sum += emp.getSalary();
			}
			return sum;
		}
	}

}
