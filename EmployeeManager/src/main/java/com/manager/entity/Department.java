package com.manager.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name="departments")
public class Department {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=2,max=50,message="length shoud be in between 2 to 50")
	private String name;
	//private Long managerId;
	
	@OneToOne
	@JoinColumn(name="managerId")
	Employee employee;
	
	@OneToMany(mappedBy ="department" )
	List<TeamProject> teamProject;
	
	
	@OneToMany(mappedBy ="department" )
	List<Employee> employees;


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


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public List<TeamProject> getTeamProject() {
		return teamProject;
	}


	public void setTeamProject(List<TeamProject> teamProject) {
		this.teamProject = teamProject;
	}


	public List<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
	
	
	
	
	
	
	
}
