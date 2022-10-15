package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.DepartmentException;
import com.example.demo.models.Department;
import com.example.demo.models.validations.DepartmentPUTValidations;
import com.example.demo.services.DepartmentService;

@RestController
@Validated
public class DepartmentController {

	@Autowired
	DepartmentService ds;

	// Get department
	@GetMapping(path = "/api/departments")
	public Iterable<Department> getDepartments() {
		return ds.findDepartments();
	}

	// Update department
	@Validated(DepartmentPUTValidations.class)
	@PutMapping(path = "/api/departments/{did}")
	public void updateDepartment(@PathVariable String did, @Valid @RequestBody Department d) {
		try {
			ds.updateDept(did, d);
		} catch (DepartmentException dx) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, dx.getMessage());
		}
	}

	// Get department total salary
	@GetMapping(path = "/api/departments/totalSalary/{did}")
	public double totalSalary(@PathVariable String did) {
		try {
			return ds.sumSalary(did);
		} catch (DepartmentException dx) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, dx.getMessage());
		}
	}

}
