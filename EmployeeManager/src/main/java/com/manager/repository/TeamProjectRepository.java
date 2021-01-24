package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.entity.TeamProject;

@Repository
public interface TeamProjectRepository extends JpaRepository<TeamProject, Long> {


}
