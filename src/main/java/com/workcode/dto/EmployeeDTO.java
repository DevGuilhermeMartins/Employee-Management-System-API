package com.workcode.dto;

import java.time.LocalDate;

import com.workcode.model.Department;
import com.workcode.model.Position;

import lombok.Data;

@Data
public class EmployeeDTO {

	private String firstName;
	private String lastName;
	private Double salary;
	private LocalDate admissionDate;
	private String email;
	private Department department;
	private Position position;
}
