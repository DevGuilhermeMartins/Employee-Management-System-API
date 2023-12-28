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

import com.workcode.dto.PositionDTO;
import com.workcode.model.Position;
import com.workcode.service.PositionService;

@RestController
@RequestMapping("/positions")
public class PositionController {

	@Autowired
	private PositionService posService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<PositionDTO> registerPosition(@RequestBody PositionDTO posDto){
		// Convert DTO to Entity
		var posEntity = modelMapper.map(posDto, Position.class);
		
		var posCreate = posService.registerPosition(posEntity);
		
		// Convert Entity to DTO
		var toPositionDto = modelMapper.map(posCreate, PositionDTO.class);
		
		return new ResponseEntity<PositionDTO>(toPositionDto, HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PositionDTO> searchAllPositions(){
		return posService.searchAllPositions().stream().map(pos -> modelMapper.map(pos, PositionDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PositionDTO> searchPositionById(@PathVariable Long id){
		Position pos = posService.searchPositionById(id);
		
		// Convert Entity to DTO
		var posResponse = modelMapper.map(pos, PositionDTO.class);
		
		return ResponseEntity.ok().body(posResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PositionDTO> updatePositionData(@PathVariable Long id, @RequestBody PositionDTO posloyeeDTO){
		// Convert DTO to Entity
		var posEntity = modelMapper.map(posloyeeDTO, Position.class);
		
		var posUpdated = posService.updatePositionData(id, posEntity);
		
		// Convert Entity to DTO
		var posResponse = modelMapper.map(posUpdated, PositionDTO.class);
		
		return ResponseEntity.ok().body(posResponse);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePosition(@PathVariable Long id){
		posService.deletePosition(id);
		return ResponseEntity.noContent().build();
	}
}
