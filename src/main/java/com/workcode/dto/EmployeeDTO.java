package com.workcode.dto;

import java.time.LocalDate;

import com.workcode.model.Department;
import com.workcode.model.Position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	private String firstName;
	private LocalDate admissionDate;
	private Department department;
	private Position position;
}
