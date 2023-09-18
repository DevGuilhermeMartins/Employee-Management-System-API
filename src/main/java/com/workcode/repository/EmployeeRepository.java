package com.workcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workcode.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
