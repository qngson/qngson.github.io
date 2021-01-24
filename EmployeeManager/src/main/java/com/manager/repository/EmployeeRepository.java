package com.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.manager.entity.Employee;



@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value="SELECT * FROM employees u ORDER BY u.id ASC LIMIT 1500",nativeQuery = true)
	   public List<Employee> getEmp();

}
