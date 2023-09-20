package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Doctor;
import com.example.demo.model.DoctorAvailability;
import com.example.demo.model.DoctorRegistrationRequest;
import com.example.demo.repository.DoctorAvailabilityRepository;
import com.example.demo.repository.DoctorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private DoctorAvailabilityRepository doctorAvailabilityRepository;

	@Transactional
	public Doctor registerDoctor(DoctorRegistrationRequest doctorRegistrationRequest) throws JsonProcessingException {

		Optional<Doctor> existingdoctor = doctorRepository
				.findByEmail(doctorRegistrationRequest.getDoctor().getEmail());
		if (existingdoctor.isPresent()) {
			throw new EntityExistsException("doctor with this email already exist.");
		}
		Doctor doctor = doctorRepository.save(doctorRegistrationRequest.getDoctor());

		DoctorAvailability doctorAvailability = new DoctorAvailability();
		doctorAvailability.setDoctor(doctorRegistrationRequest.getDoctor());
		doctorAvailability.setTimeSlot(doctorRegistrationRequest.getTimeSlot());

		List<String> workingDays = doctorRegistrationRequest.getWorkingDays();
		ObjectMapper objectMapper = new ObjectMapper();
		String workingDaysJson = objectMapper.writeValueAsString(workingDays);
		System.out.println(workingDays);
		doctorAvailability.setWorkingDays(workingDaysJson);

		doctorAvailabilityRepository.save(doctorAvailability);
		return doctor;
	}

	@Transactional
	public Optional<Doctor> login(String email, String password) {
		Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);

		if (optionalDoctor.isPresent()) {
			Doctor doctor = optionalDoctor.get();
			if (doctor.getPassword().equals(password)) {
				return Optional.of(doctor);
			}
		}

		return Optional.empty();
	}

	@Transactional(readOnly = true)
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Doctor> getDoctorById(Integer id) {
		return doctorRepository.findById(id);
	}

	@Transactional
	public void deleteDoctor(Integer id) {
		doctorRepository.deleteById(id);
	}

	public DoctorRegistrationRequest updateDoctor(DoctorRegistrationRequest doctorRegistrationRequest) {

		Optional<Doctor> currentDoctor = doctorRepository.findById(doctorRegistrationRequest.getDoctor().getId());
		if (currentDoctor.isEmpty()) {
			throw new EntityNotFoundException("doctor does not exist");
		}
		Doctor doctor = currentDoctor.get();
		doctor.setEmail(doctorRegistrationRequest.getDoctor().getEmail());
		doctor.setName(doctorRegistrationRequest.getDoctor().getEmail());
		doctor.setPassword(doctorRegistrationRequest.getDoctor().getPassword());
		doctor.setSpecialization(doctorRegistrationRequest.getDoctor().getSpecialization());
		doctorRepository.save(doctorRegistrationRequest.getDoctor());

		DoctorAvailability doctorAvailability = doctorAvailabilityRepository
				.findByDoctorId(doctorRegistrationRequest.getDoctor().getId());
		doctorAvailability.setTimeSlot(doctorRegistrationRequest.getTimeSlot());
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String workingDaysJson = objectMapper.writeValueAsString(doctorRegistrationRequest.getWorkingDays());
			doctorAvailability.setWorkingDays(workingDaysJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		doctorAvailabilityRepository.save(doctorAvailability);

		return doctorRegistrationRequest;
	}

}
