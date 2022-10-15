package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.demo.models.validations.EmployeePOSTValidations;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employee {

	// variables declaration, getters, setters
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	@NotNull(message = "eid must be provided", groups = EmployeePOSTValidations.class)
	private String eid;
	@NotNull(message = "name must be provided", groups = EmployeePOSTValidations.class)
	private String name;
	@Min(value = 20000, message = "must be greater than or equal to 20000", groups = EmployeePOSTValidations.class)
	private double salary;
	// avoid bidirectional referencing which makes result infinite
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Department dept;
	@ManyToMany
	@JsonIgnore
	private List<Project> projects;

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}
