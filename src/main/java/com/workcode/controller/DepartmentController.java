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

import com.workcode.dto.DepartmentDTO;
import com.workcode.model.Department;
import com.workcode.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<DepartmentDTO> registerDepartment(@RequestBody DepartmentDTO depDto){
		// Convert DTO to Entity
		var depEntity = modelMapper.map(depDto, Department.class);
		
		var depCreate = departService.registerDepartment(depEntity);
		
		// Convert Entity to DTO
		var toDepartmentDto = modelMapper.map(depCreate, DepartmentDTO.class);
		
		return new ResponseEntity<DepartmentDTO>(toDepartmentDto, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<DepartmentDTO> searchAllDepartments(){
		return departService.searchAllDepartments().stream().map(dep -> modelMapper.map(dep, DepartmentDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DepartmentDTO> searchDepartmentById(@PathVariable Long id){
		Department dep = departService.searchDepartmentById(id);
		
		// Convert Entity to DTO
		var depResponse = modelMapper.map(dep, DepartmentDTO.class);
		
		return ResponseEntity.ok().body(depResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<DepartmentDTO> updateDepartmentData(@PathVariable Long id, @RequestBody DepartmentDTO deployeeDTO){
		// Convert DTO to Entity
		var depEntity = modelMapper.map(deployeeDTO, Department.class);
		
		var depUpdated = departService.updateDepartmentData(id, depEntity);
		
		// Convert Entity to DTO
		var depResponse = modelMapper.map(depUpdated, DepartmentDTO.class);
		
		return ResponseEntity.ok().body(depResponse);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
		departService.deleteDepartment(id);
		return ResponseEntity.noContent().build();
	}
}
