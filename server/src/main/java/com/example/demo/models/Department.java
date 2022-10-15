package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.example.demo.models.validations.DepartmentPUTValidations;

@Entity
public class Department {

	// variables declaration, getters, setters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Null(message = "did must not be provided", groups = DepartmentPUTValidations.class)
	private String did;
	@NotNull(message = "name must be provided", groups = DepartmentPUTValidations.class)
	private String name;
	@NotNull(message = "location must be provided", groups = DepartmentPUTValidations.class)
	private String location;
	@OneToMany(mappedBy = "dept")
	private List<Employee> employees;

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
