package com.manager.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="employees")
public class Employee {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	@NotNull
	@Size(min=2,max=50,message="length shoud be in between 2 to 50")
	private String name;
	@Column(name="position")
	@NotBlank(message="position is not empty")
	private String position;
	@Column(name="salary")
	@NotBlank(message="salary is not empty")
	private Double salary;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	List<Department> departments;
	
	
	//private Long departmentId;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="departmentId")
	Department department;
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	List<EmployeeTeamProject> empteamprojects;
	@JsonIgnore
	@OneToMany(mappedBy ="employee" )
	List<TeamProject> teamProject;

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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<EmployeeTeamProject> getEmpteamprojects() {
		return empteamprojects;
	}

	public void setEmpteamprojects(List<EmployeeTeamProject> empteamprojects) {
		this.empteamprojects = empteamprojects;
	}

	public List<TeamProject> getTeamProject() {
		return teamProject;
	}

	public void setTeamProject(List<TeamProject> teamProject) {
		this.teamProject = teamProject;
	}

	
	
	
	
	
	
	

	
	
	
	
	
}
