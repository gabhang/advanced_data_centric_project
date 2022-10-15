package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

	// find project by using project id
	public Optional<Project> findByPid(String pid); // select * from projects where pid = "?"

}
