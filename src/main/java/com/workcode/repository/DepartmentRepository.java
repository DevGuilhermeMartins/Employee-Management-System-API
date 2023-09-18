package com.workcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workcode.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
