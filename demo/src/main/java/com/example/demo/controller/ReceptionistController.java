package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Receptionist;
import com.example.demo.service.ReceptionistService;

@RestController
@RequestMapping("/api/receptionists")
public class ReceptionistController {

	@Autowired
	private ReceptionistService receptionistService;

	@PostMapping("/register")
	public ResponseEntity<Receptionist> registerReceptionist(@RequestBody Receptionist receptionist) {
		Receptionist registeredReceptionist = receptionistService.registerReceptionist(receptionist);
		return new ResponseEntity<>(registeredReceptionist, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Receptionist>> getAllReceptionists() {
		List<Receptionist> receptionists = receptionistService.getAllReceptionists();
		return new ResponseEntity<>(receptionists, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Receptionist> getReceptionistById(@PathVariable Long id) {
		Optional<Receptionist> receptionist = receptionistService.getReceptionistById(id);
		if (receptionist.isPresent()) {
			return new ResponseEntity<>(receptionist.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReceptionist(@PathVariable Long id) {
		receptionistService.deleteReceptionist(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// Other endpoints for receptionist-related operations

	// ...
}
