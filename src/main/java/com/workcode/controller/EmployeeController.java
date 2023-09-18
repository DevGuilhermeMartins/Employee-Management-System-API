package com.workcode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workcode.model.Employee;
import com.workcode.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@PostMapping
	public ResponseEntity<Employee> registerEmployee(@RequestBody Employee emp){
		var empRegistered = empService.registerEmployee(emp);
		return new ResponseEntity<>(empRegistered, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> searchAllEmployees(){
		var emps = empService.searchAllEmployees();
		return ResponseEntity.ok().body(emps);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> searchEmployeeById(@PathVariable Long id){
		Employee emp = empService.searchEmployeeById(id);
		return ResponseEntity.ok().body(emp);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Employee> updateEmployeeData(@PathVariable Long id, @RequestBody Employee emp){
		emp = empService.updateEmployeeData(id, emp);
		return ResponseEntity.ok().body(emp);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
		empService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
