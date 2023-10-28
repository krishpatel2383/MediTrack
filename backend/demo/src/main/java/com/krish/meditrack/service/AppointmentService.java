package com.krish.meditrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krish.meditrack.model.Appointment;
import com.krish.meditrack.repository.AppointmentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Transactional
	public Appointment createAppointment(Appointment appointment) {
		// You can add validation and additional logic here
		return appointmentRepository.save(appointment);
	}

	@Transactional(readOnly = true)
	public List<Appointment> getAppointmentsByPatientId(int patientId) {
		return appointmentRepository.findByPatientId(patientId);
	}

	@Transactional(readOnly = true)
	public Optional<Appointment> getAppointmentById(int appointmentId) {
		return appointmentRepository.findById(appointmentId);
	}

	@Transactional
	public void deleteAppointment(int appointmentId) {
		appointmentRepository.deleteById(appointmentId);
	}

	public List<Appointment> getAppointmentsByDoctorIdAndStatus(int doctorId, String status) {
		return appointmentRepository.findByDoctorIdAndStatus(doctorId, status);
	}

	public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
		return appointmentRepository.findByDoctorId(doctorId);
	}

	public Appointment updateAppointment(Appointment updatedAppointment) {
		Optional<Appointment> currentOptional = appointmentRepository.findById(updatedAppointment.getId());
		if (currentOptional.isEmpty()) {
			throw new EntityNotFoundException("appointment does not exist");
		}
		Appointment currentAppointment = currentOptional.get();
		currentAppointment.setAppointmentDatetime(updatedAppointment.getAppointmentDatetime());
		currentAppointment.setcategory(updatedAppointment.getcategory());
		currentAppointment.setDoctorId(updatedAppointment.getDoctorId());
		currentAppointment.setPatientId(updatedAppointment.getPatientId());
		currentAppointment.setStatus(updatedAppointment.getStatus());

		return appointmentRepository.save(currentAppointment);
	}
}
