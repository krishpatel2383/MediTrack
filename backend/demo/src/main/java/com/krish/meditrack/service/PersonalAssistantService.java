package com.krish.meditrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krish.meditrack.model.Doctor;
import com.krish.meditrack.model.PersonalAssistant;
import com.krish.meditrack.repository.DoctorRepository;
import com.krish.meditrack.repository.PersonalAssistantRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonalAssistantService {
	@Autowired
	private PersonalAssistantRepository personalAssistantRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	// Create a new personal assistant
	public PersonalAssistant createPersonalAssistant(PersonalAssistant personalAssistant) {
		Doctor associatedDoctor = doctorRepository.findById(personalAssistant.getDoctor().getId()).orElse(null);
		if (associatedDoctor != null) {
			personalAssistant.setDoctor(associatedDoctor);
			return personalAssistantRepository.save(personalAssistant);
		}
		throw new EntityNotFoundException("doctor does not exist");
	}

	// Get a personal assistant by ID
	public PersonalAssistant getPersonalAssistantById(int id) {
		return personalAssistantRepository.findById(id).orElse(null);
	}

	// Update a personal assistant
	public PersonalAssistant updatePersonalAssistant(int id, PersonalAssistant updatedPersonalAssistant) {
		PersonalAssistant existingPersonalAssistant = personalAssistantRepository.findById(id).orElse(null);
		if (existingPersonalAssistant != null) {
			// Update fields as needed
			existingPersonalAssistant.setName(updatedPersonalAssistant.getName());
			existingPersonalAssistant.setEmail(updatedPersonalAssistant.getEmail());
			existingPersonalAssistant.setPassword(updatedPersonalAssistant.getPassword());
			existingPersonalAssistant.setPhone(updatedPersonalAssistant.getPhone());
			existingPersonalAssistant
					.setDoctor(doctorRepository.findById(updatedPersonalAssistant.getDoctor().getId()).get());
			return personalAssistantRepository.save(existingPersonalAssistant);
		}
		throw new EntityNotFoundException("assistant not found");
	}

	// Delete a personal assistant by ID
	public boolean deletePersonalAssistant(int id) {
		PersonalAssistant personalAssistant = personalAssistantRepository.findById(id).orElse(null);
		if (personalAssistant != null) {
			personalAssistantRepository.delete(personalAssistant);
			return true;
		}
		return false;
	}

	// Add other service methods as needed
}
