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

import com.workcode.model.Department;
import com.workcode.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departService;
	
	@PostMapping
	public ResponseEntity<Department> registerDepartment(@RequestBody Department depart){
		var departRegistered = departService.registerDepartment(depart);
		return new ResponseEntity<>(departRegistered, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Department>> searchAllDepartments(){
		var departs = departService.searchAllDepartments();
		return ResponseEntity.ok().body(departs);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Department> searchDepartmentById(@PathVariable Long id){
		Department depart = departService.searchDepartmentById(id);
		return ResponseEntity.ok().body(depart);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Department> updateDepartmentData(@PathVariable Long id, @RequestBody Department depart){
		depart = departService.updateDepartmentData(id, depart);
		return ResponseEntity.ok().body(depart);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
		departService.deleteDepartment(id);
		return ResponseEntity.noContent().build();
	}
}
