package com.manager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employeeteamproject")
public class EmployeeTeamProject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="teamProjectId")
	TeamProject teamProject;
	//private Long teamProjectId;
	//private Long employeeId;
	
	@ManyToOne
	@JoinColumn(name="employeeId")
	Employee employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TeamProject getTeamProject() {
		return teamProject;
	}

	public void setTeamProject(TeamProject teamProject) {
		this.teamProject = teamProject;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	

	
	
	
	
	
}
