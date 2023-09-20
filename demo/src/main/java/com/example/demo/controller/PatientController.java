package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping("/register")
	public ResponseEntity<?> registerPatient(@RequestBody Patient patient) {
		Patient registeredPatient;
		try {
			registeredPatient = patientService.registerPatient(patient);
		} catch (EntityExistsException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(registeredPatient, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginPatient(@RequestBody Patient p) {
		Optional<Patient> patient = patientService.login(p.getEmail(), p.getPassword());
		if (patient.isPresent()) {
			return new ResponseEntity<>(patient.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED); // Authentication failed
		}
	}

	@PostMapping("/appointments")
	public ResponseEntity<?> requestAppointment(@RequestBody Appointment appointment) {
		try {
			Appointment appointment2 = patientService.requestAppointment(appointment);
			return new ResponseEntity<>(appointment2, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // time slot unavailable.
		} catch (NotFoundException e) {
			return new ResponseEntity<>("doctor not found", HttpStatus.NOT_FOUND); // Doctor not found
		}
	}

	@GetMapping("/{patientId}/appointments")
	public ResponseEntity<List<Appointment>> getPatientAppointments(@PathVariable int patientId) {
		List<Appointment> appointments = patientService.getPatientAppointments(patientId);
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}

	@GetMapping("/{patientId}")
	public ResponseEntity<?> getPatientById(@PathVariable int patientId) {
		Optional<Patient> patient = patientService.getPatientById(patientId);
		if (patient.isPresent()) {
			return new ResponseEntity<>(patient.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("patient not found with id: " + patientId, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patients = patientService.getAllPatients();
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updatePatient(@RequestBody Patient updatedPatient) {
		try {
			Patient patient = patientService.updatePatient(updatedPatient);
			return new ResponseEntity<>(patient, HttpStatus.OK);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{patientId}")
	public ResponseEntity<?> deletePatient(@PathVariable int patientId) {
		try {
			patientService.deletePatient(patientId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
