package com.workcode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workcode.model.Position;
import com.workcode.repository.PositionRepository;

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
		Optional<Position> pos = posRepository.findById(id);
		return pos.orElseThrow(() -> new RuntimeException());
	}

	// U - Update Position
	public Position updatePositionData(Long id, Position posData) {
		Position posEntity = posRepository.getReferenceById(id);
		updateData(posEntity, posData);
		return posRepository.save(posEntity);
	}

	// D - Delete Position
	public void deletePosition(Long id) {
		Position pos = posRepository.getReferenceById(id);
		posRepository.delete(pos);
	}

	private void updateData(Position posEntity, Position posData) {
		posEntity.setName(posData.getName());
	}
}
