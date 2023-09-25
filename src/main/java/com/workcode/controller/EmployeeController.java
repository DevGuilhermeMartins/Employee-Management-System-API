package com.workcode.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.workcode.dto.EmployeeDTO;
import com.workcode.model.Employee;
import com.workcode.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> registerEmployee(@RequestBody EmployeeDTO empDto){
		// Convert DTO to Entity
		var empEntity = modelMapper.map(empDto, Employee.class);
		
		var empCreate = empService.registerEmployee(empEntity);
		
		// Convert Entity to DTO
		var toEmployeeDto = modelMapper.map(empCreate, EmployeeDTO.class);
		
		return new ResponseEntity<EmployeeDTO>(toEmployeeDto, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<EmployeeDTO> searchAllEmployees(){
		return empService.searchAllEmployees().stream().map(emp -> modelMapper.map(emp, EmployeeDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> searchEmployeeById(@PathVariable Long id){
		Employee emp = empService.searchEmployeeById(id);
		
		// Convert Entity to DTO
		var empResponse = modelMapper.map(emp, EmployeeDTO.class);
		
		return ResponseEntity.ok().body(empResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> updateEmployeeData(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
		// Convert DTO to Entity
		var empEntity = modelMapper.map(employeeDTO, Employee.class);
		
		var empUpdated = empService.updateEmployeeData(id, empEntity);
		
		// Convert Entity to DTO
		var empResponse = modelMapper.map(empUpdated, EmployeeDTO.class);
		
		return ResponseEntity.ok().body(empResponse);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
		empService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
