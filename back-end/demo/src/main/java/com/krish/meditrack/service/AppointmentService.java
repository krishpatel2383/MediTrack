package com.krish.meditrack.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krish.meditrack.model.Appointment;
import com.krish.meditrack.model.Doctor;
import com.krish.meditrack.model.PersonalAssistant;
import com.krish.meditrack.repository.AppointmentRepository;
import com.krish.meditrack.repository.PersonalAssistantRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PersonalAssistantRepository personalAssistantRepository;

	public List<Appointment> getAppointmentsByPatientIdAndStatus(int patientId, String status) {
		return appointmentRepository.findAllByPatientIdAndStatus(patientId, status);
	}

	public List<Appointment> getAppointmentsByDoctorIdAndStatus(int doctorId, String status) {
		return appointmentRepository.findByDoctorIdAndStatus(doctorId, status);
	}

	public List<Appointment> getAppointmentsByPersonalAssistantIdAndStatus(int PersonalAssistantId, String status) {
		Optional<PersonalAssistant> personalAssistant = personalAssistantRepository.findById(PersonalAssistantId);
		if (personalAssistant.isPresent()) {
			Doctor doctor = personalAssistant.get().getDoctor();
			return getAppointmentsByDoctorIdAndStatus(doctor.getId(), status);
		}
		return Collections.emptyList();
	}

	public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
		return appointmentRepository.findByDoctorId(doctorId);
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
	public Appointment createAppointment(Appointment appointment) {
		// You can add validation and additional logic here
		return appointmentRepository.save(appointment);
	}

	@Transactional
	public Appointment requestAppointment(Appointment appointment) throws NotFoundException {

		System.out.println(appointment.getDescription());
		Appointment appointment2 = new Appointment();
		appointment2.setPatientId(appointment.getPatientId());
		appointment2.setStatus("requested");
		appointment2.setCategory(appointment.getCategory());
		appointment2.setAppointmentTimestamp(new Timestamp(System.currentTimeMillis()));
		appointment2.setDescription(appointment.getDescription());
		appointmentRepository.save(appointment2);

		return appointment2;

	}

	@Transactional
	public void deleteAppointment(int appointmentId) {
		appointmentRepository.deleteById(appointmentId);
	}

	public Appointment updateAppointment(Appointment updatedAppointment) {
		Optional<Appointment> currentOptional = appointmentRepository.findById(updatedAppointment.getId());
		if (currentOptional.isEmpty()) {
			throw new EntityNotFoundException("appointment does not exist");
		}
		Appointment currentAppointment = currentOptional.get();
		currentAppointment.setAppointmentDatetime(updatedAppointment.getAppointmentDatetime());
		currentAppointment.setCategory(updatedAppointment.getCategory());
		currentAppointment.setDoctorId(updatedAppointment.getDoctorId());
		currentAppointment.setPatientId(updatedAppointment.getPatientId());
		currentAppointment.setStatus(updatedAppointment.getStatus());

		return appointmentRepository.save(currentAppointment);
	}
}
