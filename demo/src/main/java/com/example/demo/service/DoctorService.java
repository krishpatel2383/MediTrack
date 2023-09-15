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

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private DoctorAvailabilityRepository doctorAvailabilityRepository;

	@Transactional
	public void registerDoctor(DoctorRegistrationRequest doctorRegistrationRequest) throws JsonProcessingException {
		// You can add validation and additional logic here
		doctorRepository.save(doctorRegistrationRequest.getDoctor());

		DoctorAvailability doctorAvailability = new DoctorAvailability();
		doctorAvailability.setDoctor(doctorRegistrationRequest.getDoctor());
		doctorAvailability.setTimeSlot(doctorRegistrationRequest.getTimeSlot());
		// serializing to json
		List<String> workingDays = doctorRegistrationRequest.getWorkingDays();
		ObjectMapper objectMapper = new ObjectMapper();
		String workingDaysJson = objectMapper.writeValueAsString(workingDays);
		System.out.println(workingDays);
		doctorAvailability.setWorkingDays(workingDaysJson);

		doctorAvailabilityRepository.save(doctorAvailability);

	}

	@Transactional
	public Optional<Doctor> login(String email, String password) {
		Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(email);

		if (optionalDoctor.isPresent()) {
			Doctor doctor = optionalDoctor.get();
			// Compare the hashed password (you should use a secure comparison method)
			// For simplicity, we'll assume the password is already hashed
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
	public Optional<Doctor> getDoctorById(Long id) {
		return doctorRepository.findById(id);
	}

	@Transactional
	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}

	// Other methods for doctor-related operations

	// ...
}
