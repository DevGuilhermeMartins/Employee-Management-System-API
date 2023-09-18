package com.workcode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workcode.model.Department;
import com.workcode.repository.DepartmentRepository;

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
		Optional<Department> depart = departRepository.findById(id);
		return depart.orElseThrow(() -> new RuntimeException());
	}

	// U - Update Department
	public Department updateDepartmentData(Long id, Department departData) {
		Department departEntity = departRepository.getReferenceById(id);
		updateData(departEntity, departData);
		return departRepository.save(departEntity);
	}

	// D - Delete Department
	public void deleteDepartment(Long id) {
		Department depart = departRepository.getReferenceById(id);
		departRepository.delete(depart);
	}

	private void updateData(Department departEntity, Department departData) {
		departEntity.setName(departData.getName());
	}
}
