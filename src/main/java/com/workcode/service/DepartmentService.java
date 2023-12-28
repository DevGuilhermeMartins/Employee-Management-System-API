package com.workcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workcode.model.Department;
import com.workcode.repository.DepartmentRepository;
import com.workcode.service.exceptions.ResourceNotFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departRepository;

	// CRUD

	// C - Register Department
	public Department registerDepartment(Department depart) {
		return departRepository.save(depart);
	}

	// R - Search all Departments
	public List<Department> searchAllDepartments() {
		return departRepository.findAll();
	}

	// R - Search Department By ID
	public Department searchDepartmentById(Long id) {
		return departRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	// U - Update Department
	public Department updateDepartmentData(Long id, Department departData) {
		Department departEntity = departRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(departEntity, departData);
		return departRepository.save(departEntity);
	}

	// D - Delete Department
	public void deleteDepartment(Long id) {
		Department depart = departRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		departRepository.delete(depart);
	}

	private void updateData(Department departEntity, Department departData) {
		departEntity.setDepartmentName(departData.getDepartmentName());
	}
}
