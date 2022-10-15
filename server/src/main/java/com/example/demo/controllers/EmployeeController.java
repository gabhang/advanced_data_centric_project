package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.EmployeeException;
import com.example.demo.models.Employee;
import com.example.demo.models.validations.EmployeePOSTValidations;
import com.example.demo.services.EmployeeService;

@RestController
@Validated
public class EmployeeController {

	@Autowired
	EmployeeService es;

	@GetMapping(path = "/api/employees")
	public Iterable<Employee> getEmployees() {
		return es.findEmployees();
	}

	@DeleteMapping(path = "/api/employees/{eid}")
	public void deleteEmployees(@PathVariable String eid) {
		try {
			es.findEmployeesByEid(eid);
			try {
				es.deleteEmployeeByEid(eid);
			} catch (EmployeeException ex) {
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
			}
		} catch (EmployeeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@Validated(EmployeePOSTValidations.class)
	@PostMapping(path = "/api/employees")
	public void saveEmployee(@Valid @RequestBody Employee e) {
		try {
			es.save(e);
		} catch (EmployeeException ex) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
		}
	}

}
