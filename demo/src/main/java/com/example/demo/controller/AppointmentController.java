package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

//	@PostMapping("/create")
//	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
//		Appointment createdAppointment = appointmentService.createAppointment(appointment);
//		return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
//	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable int patientId) {
		List<Appointment> appointments = appointmentService.getAppointmentsByPatientId(patientId);
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

	@DeleteMapping("/{appointmentId}")
	public ResponseEntity<Void> deleteAppointment(@PathVariable int appointmentId) {
		appointmentService.deleteAppointment(appointmentId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/doctor/{doctorId}/scheduled")
	public ResponseEntity<List<Appointment>> getAppointmentsByDoctorIdAndStatus(@PathVariable int doctorId) {
		String status = "Scheduled"; // Define the status you want to filter by
		List<Appointment> appointments = appointmentService.getAppointmentsByDoctorIdAndStatus(doctorId, status);
		return new ResponseEntity<>(appointments, HttpStatus.OK);
	}
}
