package com.manager.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="teamproject")
public class TeamProject {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	//private Long managerId;
	@ManyToOne
	@JoinColumn(name="departmentId")
	Department department;
	
	//private Long departmentId;
	@ManyToOne
	@JoinColumn(name="managerId")
	Employee employee;
	
	
	@OneToMany(mappedBy ="teamProject" )
	List<EmployeeTeamProject> teamProject;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public List<EmployeeTeamProject> getTeamProject() {
		return teamProject;
	}


	public void setTeamProject(List<EmployeeTeamProject> teamProject) {
		this.teamProject = teamProject;
	}
	
	
	
	
	
}
