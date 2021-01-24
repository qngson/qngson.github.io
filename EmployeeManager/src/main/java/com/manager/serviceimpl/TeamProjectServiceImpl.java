package com.manager.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.entity.TeamProject;
import com.manager.repository.TeamProjectRepository;
import com.manager.service.TeamProjectService;

@Service
public class TeamProjectServiceImpl implements TeamProjectService {

	@Autowired
	TeamProjectRepository teamProjectRepository;

	@Override
	public List<TeamProject> findAllTeamProject() {
		return teamProjectRepository.findAll();
	}

	@Override
	public void saveTeamProject(TeamProject teamProject) {
		this.teamProjectRepository.save(teamProject);
		
	}

	@Override
	public TeamProject getTeamProjectById(long id) {
		Optional<TeamProject> optional = teamProjectRepository.findById(id);
		TeamProject teamProject = null;
		if(optional.isPresent()) {
			teamProject = optional.get();
		}else {
			throw new RuntimeException(" TeamProject not found for id::" +id);
		}
		return teamProject;
		
	}

	@Override
	public void deleteTeamProjectById(long id) {
		this.teamProjectRepository.deleteById(id);
	}

}
