package com.demo.springrestapi.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
//
import com.demo.springrestapi.model.Employee;
import com.demo.springrestapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private static List<Employee> empList = new ArrayList<Employee>();
	
//	static {
//		Employee employee1 = new Employee();
//		employee1.setAge(23L);
//		employee1.setDepartment("ECE");
//		employee1.setEmail("raju@gmail.com");
//		employee1.setLocation("Hyderabad");
//		employee1.setName("Raju");
//		
//		Employee employee2 = new Employee();
//		employee2.setAge(23L);
//		employee2.setDepartment("CSE");
//		employee2.setEmail("ram@gmail.com");
//		employee2.setLocation("Hyderabad");
//		employee2.setName("Ram");
//		
//		empList.add(employee1);	
//		empList.add(employee2);
//		
//	}
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Override
	public List<Employee> getEmployees(int pageNumber,int pageSize) {
		Pageable pages = PageRequest.of(pageNumber, pageSize, Direction.DESC,"id");
		return empRepository.findAll(pages).getContent();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return empRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> emp = empRepository.findById(id);
		if(emp.isPresent()) {
			return emp.get();
		}
		throw new RuntimeException("Employee is not found with id "+id);
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		employee.setId(id);
		return empRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Long id) {
		empRepository.deleteById(id);
	}

	@Override
	public List<Employee> getEmployeeByName(String name) {
		return empRepository.findByName(name);
	}

	@Override
	public List<Employee> getEmployeeByNameAndLocation(String name, String location) {
		return empRepository.findByNameAndLocation(name, location);
	}

	@Override
	public List<Employee> getEmployeesContaining(String name) {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return empRepository.findByNameContaining(name,sort);
	}

	@Override
	public List<Employee> getEmployeesByNameOrLocation(String name, String location) {
		return empRepository.getEmployeesByNameAndLocation(name, location);
	}

	@Override
	public Integer DeleteEmployeeByName(String name) {
		return empRepository.deleteEmployeeByName(name);
	}

}
