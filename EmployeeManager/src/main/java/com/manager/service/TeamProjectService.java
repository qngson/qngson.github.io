package com.manager.service;

import java.util.List;

import com.manager.entity.TeamProject;

public interface TeamProjectService {

	List<TeamProject> findAllTeamProject();
	void saveTeamProject(TeamProject teamProject);
	TeamProject getTeamProjectById(long id);
	void deleteTeamProjectById(long id);
}
