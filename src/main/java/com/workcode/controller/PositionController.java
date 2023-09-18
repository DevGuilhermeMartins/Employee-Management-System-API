package com.workcode.controller;

import java.util.List;

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

import com.workcode.model.Position;
import com.workcode.service.PositionService;

@RestController
@RequestMapping("/positions")
public class PositionController {

	@Autowired
	private PositionService posService;
	
	@PostMapping
	public ResponseEntity<Position> registerPosition(@RequestBody Position pos){
		var posRegistered = posService.registerPosition(pos);
		return new ResponseEntity<>(posRegistered, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Position>> searchAllPositions(){
		var poss = posService.searchAllPositions();
		return ResponseEntity.ok().body(poss);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Position> searchPositionById(@PathVariable Long id){
		Position pos = posService.searchPositionById(id);
		return ResponseEntity.ok().body(pos);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Position> updatePositionData(@PathVariable Long id, @RequestBody Position pos){
		pos = posService.updatePositionData(id, pos);
		return ResponseEntity.ok().body(pos);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletePosition(@PathVariable Long id){
		posService.deletePosition(id);
		return ResponseEntity.noContent().build();
	}
}
