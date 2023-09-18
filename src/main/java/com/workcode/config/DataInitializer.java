package com.workcode.config;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.workcode.model.Department;
import com.workcode.model.Employee;
import com.workcode.model.Position;
import com.workcode.repository.DepartmentRepository;
import com.workcode.repository.EmployeeRepository;
import com.workcode.repository.PositionRepository;

@Configuration
public class DataInitializer implements CommandLineRunner{

	@Autowired
	private DepartmentRepository departRepository;
	
	@Autowired
	private PositionRepository posRepository;
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		var dep1 = new Department(null, "Technology");
		var pos1 = new Position(null, "Junior Data Science");
		
		var emp1 = new Employee(null, "William", "Martin", LocalDate.parse("2019-06-20"), 9832.23
				, LocalDate.parse("2019-08-22"), "william@gmail.com", dep1, pos1);
		
		departRepository.saveAll(Arrays.asList(dep1));
		posRepository.saveAll(Arrays.asList(pos1));
		
		empRepository.saveAll(Arrays.asList(emp1));
	}

}
