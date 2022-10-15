package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.exceptions.ProjectException;
import com.example.demo.models.Project;
import com.example.demo.services.ProjectService;

@RestController
public class ProjectController {

	@Autowired
	ProjectService ps;

	// Get projects
	@GetMapping(path = "api/projects")
	public Iterable<Project> getProjects() {
		return ps.findProjects();
	}

	// Delete project
	@DeleteMapping(path = "/api/projects/{pid}")
	public void deleteProject(@PathVariable String pid) {
		try {
			ps.deleteProject(pid);
		} catch (ProjectException px) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, px.getMessage());
		}
	}
}
