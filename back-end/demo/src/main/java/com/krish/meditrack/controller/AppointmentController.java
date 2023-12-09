package com.krish.meditrack.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krish.meditrack.model.Appointment;
import com.krish.meditrack.service.AppointmentService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	// get by patient and status = requested, scheduled and completed
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<List<Appointment>> getAppointmentsByPatientIdAndStatus(@PathVariable int patientId,
			@RequestParam String status) {
		List<Appointment> appointments = appointmentService.getAppointmentsByPatientIdAndStatus(patientId, status);
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}

	@GetMapping("/doctor/{doctorId}/scheduled")
	public ResponseEntity<List<Appointment>> getAppointmentsByDoctorIdAndStatus(@PathVariable int doctorId) {
		String status = "Scheduled";
		List<Appointment> appointments = appointmentService.getAppointmentsByDoctorIdAndStatus(doctorId, status);
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}

	@GetMapping("/{appointmentId}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable int appointmentId) {
		Optional<Appointment> appointment = appointmentService.getAppointmentById(appointmentId);
		if (appointment.isPresent()) {
			return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/personalAssistant/{PersonalAssistantId}")
	public ResponseEntity<List<Appointment>> getAppointmentsByPersonalAssistantId(@PathVariable int PersonalAssistantId,
			@RequestParam String status) {
		List<Appointment> appointments = appointmentService
				.getAppointmentsByPersonalAssistantIdAndStatus(PersonalAssistantId, status);
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}

	@PostMapping("/patient/{patientId}/request")
	public ResponseEntity<?> requestAppointment(@RequestBody Appointment appointment, @PathVariable int patientId) {
		try {
			appointment.setPatientId(patientId);
			System.out.println(appointment.getDescription());
			Appointment appointment2 = appointmentService.requestAppointment(appointment);
			return new ResponseEntity<>(appointment2, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT); // time slot unavailable.
		} catch (NotFoundException e) {
			return new ResponseEntity<>("doctor not found", HttpStatus.NOT_FOUND); // Doctor not found
		}
	}

	@DeleteMapping("/delete/{appointmentId}")
	public ResponseEntity<Void> deleteAppointment(@PathVariable int appointmentId) {
		appointmentService.deleteAppointment(appointmentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) {
		try {
			Appointment updatedAppointment = appointmentService.updateAppointment(appointment);
			return ResponseEntity.ok(updatedAppointment);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
