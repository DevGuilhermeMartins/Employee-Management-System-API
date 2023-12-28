package com.workcode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workcode.model.Position;
import com.workcode.repository.PositionRepository;
import com.workcode.service.exceptions.ResourceNotFoundException;

@Service
public class PositionService {

	@Autowired
	private PositionRepository posRepository;

	// CRUD

	// C - Register Position
	public Position registerPosition(Position pos) {
		return posRepository.save(pos);
	}

	// R - Search all Positions
	public List<Position> searchAllPositions() {
		return posRepository.findAll();
	}

	// R - Search Position By ID
	public Position searchPositionById(Long id) {
		return posRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	// U - Update Position
	public Position updatePositionData(Long id, Position posData) {
		Position posEntity = posRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		updateData(posEntity, posData);
		return posRepository.save(posEntity);
	}

	// D - Delete Position
	public void deletePosition(Long id) {
		Position pos = posRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		posRepository.delete(pos);
	}

	private void updateData(Position posEntity, Position posData) {
		posEntity.setPosition_name(posData.getPosition_name());
	}
}
