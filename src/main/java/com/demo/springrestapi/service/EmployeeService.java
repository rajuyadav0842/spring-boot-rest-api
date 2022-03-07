package com.demo.springrestapi.service;

import java.util.List;
import java.util.Optional;

import com.demo.springrestapi.model.Employee;

public interface EmployeeService {
//	public List<Employee> getEmployees();

	public Employee saveEmployee(Employee employee);

	public Employee getEmployeeById(Long id);

	public Employee updateEmployee(Long id, Employee employee);

	public void deleteEmployee(Long id);

	public List<Employee> getEmployeeByName(String name);

	public List<Employee> getEmployeeByNameAndLocation(String name, String location);

	public List<Employee> getEmployeesContaining(String name);

	List<Employee> getEmployees(int pageNumber, int pageSize);
	
	List<Employee> getEmployeesByNameOrLocation(String name,String location);
	
	Integer DeleteEmployeeByName(String name);
}
