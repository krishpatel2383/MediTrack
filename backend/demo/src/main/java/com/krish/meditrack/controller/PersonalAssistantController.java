package com.krish.meditrack.controller;

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

import com.krish.meditrack.model.PersonalAssistant;
import com.krish.meditrack.service.PersonalAssistantService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/personal_assistants")
public class PersonalAssistantController {

	@Autowired
	private PersonalAssistantService personalAssistantService;

	@PostMapping("/create")
	public ResponseEntity<?> createPersonalAssistant(@RequestBody PersonalAssistant personalAssistant) {
		try {
			PersonalAssistant createdPersonalAssistant = personalAssistantService
					.createPersonalAssistant(personalAssistant);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdPersonalAssistant);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPersonalAssistantById(@PathVariable int id) {
		PersonalAssistant personalAssistant = personalAssistantService.getPersonalAssistantById(id);
		if (personalAssistant == null) {
			return new ResponseEntity<>("assistant not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(personalAssistant);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePersonalAssistant(@PathVariable int id,
			@RequestBody PersonalAssistant personalAssistant) {
		PersonalAssistant updatedPersonalAssistant = personalAssistantService.updatePersonalAssistant(id,
				personalAssistant);
		if (updatedPersonalAssistant == null) {
			return new ResponseEntity<>("assistant not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(updatedPersonalAssistant);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deletePersonalAssistant(@PathVariable int id) {
		if (personalAssistantService.deletePersonalAssistant(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
