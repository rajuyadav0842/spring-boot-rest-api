package com.demo.springrestapi.controller;


import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springrestapi.model.Employee;
import com.demo.springrestapi.service.EmployeeService;


@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@Value("${app.name}")
	private String appName;
	
//	@Value("${app.name: Employee Tracker}")
//	private String appName;
	
	@Value("${app.version}")
	private String appVersion;
	
//	@Value("${app.version: Version1}")
//	private String appVersion;
	
	@GetMapping("/app/details")
	public String getAppDetails() {
		return appName+"_"+appVersion;
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam int pageNumber,@RequestParam int pageSize) {
		return new ResponseEntity<List<Employee>>(empService.getEmployees(pageNumber, pageSize),HttpStatus.OK);
	}
	
	@GetMapping(value="/employees/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
		return new ResponseEntity<Employee>(empService.getEmployeeById(id),HttpStatus.OK);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
		return new ResponseEntity<Employee>( empService.saveEmployee(employee),HttpStatus.CREATED);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(empService.updateEmployee(id,employee),HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/employees")
	public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id) {
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam String name) {
		return new ResponseEntity<List<Employee>>(empService.getEmployeeByName(name),HttpStatus.OK);
	}
	
	@GetMapping("/employees/filterBy/nameAndLocation")
	public ResponseEntity<List<Employee>> getEmpByNameAndLocation(@RequestParam String name,@RequestParam String location){
		return new ResponseEntity<List<Employee>>(empService.getEmployeeByNameAndLocation(name,location),HttpStatus.OK);
	}
	
	@GetMapping("/employees/containing/name")
	public ResponseEntity<List<Employee>> getEmployeesContaining(@RequestParam String name){
		return new ResponseEntity<List<Employee>>(empService.getEmployeesContaining(name),HttpStatus.OK);
	}
	
	@GetMapping("/employees/{name}/{location}")
	public ResponseEntity<List<Employee>> getEmployeesByNameAndLocation(@PathVariable String name,@PathVariable String location){
		return new ResponseEntity<List<Employee>>(empService.getEmployeesByNameOrLocation(name, location),HttpStatus.OK);
	}
	
	@DeleteMapping("/employees/delete/{name}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable String name){
		return new ResponseEntity<String>(empService.DeleteEmployeeByName(name)+" Records are Deleted Justnow",HttpStatus.OK);
	}
}
