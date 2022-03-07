package com.demo.springrestapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springrestapi.model.Employee;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long>{
	List<Employee> findByName(String name);
	List<Employee> findByNameAndLocation(String name,String location);
	List<Employee> findByNameContaining(String keyword, Sort sort);
	
	@Query("From Employee Where name=:name OR location=:location")
	List<Employee> getEmployeesByNameAndLocation(String name,String location);
	
	@Transactional
	@Modifying
	@Query("Delete From Employee Where name=:name")
	Integer deleteEmployeeByName(String name);
}
