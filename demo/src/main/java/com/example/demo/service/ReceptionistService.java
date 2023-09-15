package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Receptionist;
import com.example.demo.repository.ReceptionistRepository;

@Service
public class ReceptionistService {

	@Autowired
	private ReceptionistRepository receptionistRepository;

	@Transactional
	public Receptionist registerReceptionist(Receptionist receptionist) {
		// You can add validation and additional logic here
		return receptionistRepository.save(receptionist);
	}

	@Transactional(readOnly = true)
	public List<Receptionist> getAllReceptionists() {
		return receptionistRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Receptionist> getReceptionistById(Long id) {
		return receptionistRepository.findById(id);
	}

	@Transactional
	public void deleteReceptionist(Long id) {
		receptionistRepository.deleteById(id);
	}

	// Other methods for receptionist-related operations

	// ...
}
