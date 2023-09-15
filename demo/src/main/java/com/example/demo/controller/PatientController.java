package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	// Endpoint to register a new patient
	@PostMapping("/register")
	public ResponseEntity<Patient> registerPatient(@RequestBody Patient patient) {
		Patient registeredPatient = patientService.registerPatient(patient);
		return new ResponseEntity<>(registeredPatient, HttpStatus.CREATED);
	}

	// Endpoint to log in a patient
	@PostMapping("/login")
	public ResponseEntity<Patient> loginPatient(@RequestBody Patient p) {
		Optional<Patient> patient = patientService.login(p.getEmail(), p.getPassword());
		if (patient.isPresent()) {
			return new ResponseEntity<>(patient.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Authentication failed
		}
	}

	// Endpoint to request an appointment
	@PostMapping("/appointments")
	public ResponseEntity<?> requestAppointment(@RequestBody Appointment appointment) {
		try {
			Appointment appointment2 = patientService.requestAppointment(appointment);
			return new ResponseEntity<>(appointment2, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // time slot unavailable.
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Doctor not found
		}
	}

	// Endpoint to view and track patient's appointments
	@GetMapping("/{patientId}/appointments")
	public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable int patientId) {
		List<Appointment> appointments = patientService.getPatientAppointments(patientId);
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}

}
