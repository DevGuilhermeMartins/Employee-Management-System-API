package com.workcode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workcode.model.Employee;
import com.workcode.repository.EmployeeRepository;
import com.workcode.service.exceptions.ResourceNotFoundException;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepository;
	
	// CRUD
	
	// C - Register Employee
	public Employee registerEmployee(Employee emp) { 
		return empRepository.save(emp);
	}
	
	// R - Search all Employees
	public List<Employee> searchAllEmployees(){
		return empRepository.findAll();
	}
	
	// R - Search Employee By ID
	public Employee searchEmployeeById(Long id) {
		Optional<Employee> emp = empRepository.findById(id);
		return emp.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	// U - Update Employee
	public Employee updateEmployeeData(Long id, Employee empData) {
			Employee empEntity = empRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			updateData(empEntity, empData);
			return empRepository.save(empEntity);
		
	}
	
	// D - Delete Employee
	public void deleteEmployee(Long id) {
		Employee emp = empRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		empRepository.delete(emp);
	}

	private void updateData(Employee empEntity, Employee empData) {
		empEntity.setFirstName(empData.getFirstName());
		empEntity.setLastName(empData.getLastName());
		empEntity.setBirthday(empData.getBirthday());
		empEntity.setDepartment(empData.getDepartment());
		empEntity.setPosition(empData.getPosition());
		empEntity.setSalary(empData.getSalary());
		empEntity.setAdmissionDate(empData.getAdmissionDate());
		empEntity.setEmail(empData.getEmail());
	}
}
