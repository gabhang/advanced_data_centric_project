package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ProjectException;
import com.example.demo.models.Project;
import com.example.demo.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository pr;

	// get all projects
	public Iterable<Project> findProjects() {
		return pr.findAll();
	}

	// delete project
	public void deleteProject(String pid) {
		// find if project id exists
		Optional<Project> proj = pr.findByPid(pid);
		// if does not exists, throw exception, else, delete
		if (proj.isEmpty()) {
			throw new ProjectException("project: " + pid + " not found");
		} else {
			pr.delete(proj.get());
		}
	}
}
