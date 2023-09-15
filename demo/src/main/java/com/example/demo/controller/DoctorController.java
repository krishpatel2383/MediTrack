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

import com.example.demo.model.Doctor;
import com.example.demo.model.DoctorRegistrationRequest;
import com.example.demo.service.DoctorService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/register")
	public ResponseEntity<Void> registerDoctor(@RequestBody DoctorRegistrationRequest doctorRegistrationRequest)
			throws JsonProcessingException {
//		Doctor registeredDoctor = doctorService.registerDoctor(doctorRegistrationRequest);
		doctorService.registerDoctor(doctorRegistrationRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Doctor> loginDoctor(@RequestBody Doctor d) {
		Optional<Doctor> doctor = doctorService.login(d.getEmail(), d.getPassword());
		if (doctor.isPresent()) {
			return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Authentication failed
		}
	}

	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors = doctorService.getAllDoctors();
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
		Optional<Doctor> doctor = doctorService.getDoctorById(id);
		if (doctor.isPresent()) {
			return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
		doctorService.deleteDoctor(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
