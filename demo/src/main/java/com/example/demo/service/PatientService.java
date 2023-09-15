package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.model.DoctorAvailability;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorAvailabilityRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private DoctorAvailabilityRepository doctorAvailabilityRepository;

	@Transactional
	public Patient registerPatient(Patient patient) {
		return patientRepository.save(patient);
	}

	@Transactional
	public Optional<Patient> login(String email, String password) {
		Optional<Patient> optionalPatient = patientRepository.findByEmail(email);

		if (optionalPatient.isPresent()) {
			Patient patient = optionalPatient.get();
			// Compare the hashed password (you should use a secure comparison method)
			// For simplicity, we'll assume the password is already hashed
			if (patient.getPassword().equals(password)) {
				return Optional.of(patient);
			}
		}

		return Optional.empty();
	}

	@Transactional
	public Appointment requestAppointment(Appointment appointment) throws NotFoundException {
		// Check if the requested appointment date and time are available for the
		// selected doctor
		Doctor doctor = doctorRepository.findById(appointment.getDoctorId()).orElseThrow(() -> new NotFoundException());

		if (!isAppointmentSlotAvailable(doctor, appointment.getAppointmentDatetime())) {
			throw new IllegalArgumentException("time slot unavailable.");
		}

		Appointment appointment2 = new Appointment();
		appointment2.setPatientId(appointment.getPatientId());
		appointment2.setDoctorId(appointment.getDoctorId());
		appointment2.setAppointmentDatetime(appointment.getAppointmentDatetime());
		appointment2.setReceptionistId(appointment.getReceptionistId());
		appointment2.setStatus("Scheduled");

		appointmentRepository.save(appointment2);

		return appointment2;
	}

	@Transactional(readOnly = true)
	public List<Appointment> getPatientAppointments(int patientId) {
		return appointmentRepository.findByPatientId(patientId);
	}

	private boolean isAppointmentSlotAvailable(Doctor doctor, LocalDateTime appointmentDateTime) {
		// 1. check if the doctor is available for the slot
		LocalDate appointmentDate = appointmentDateTime.toLocalDate();
		LocalTime appointmenTime = appointmentDateTime.toLocalTime();

		// convert date to day of week
		String appointmentDay = appointmentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);

		DoctorAvailability doctorAvailability = doctorAvailabilityRepository.findByDoctorId(doctor.getId());

		System.out.println("requested day:" + appointmentDay);
		System.out.println("requested time:" + appointmenTime);

		String workingDays = doctorAvailability.getWorkingDays();

		System.out.println("available days:" + workingDays);
		System.out.println("available time:" + doctorAvailability.getTimeSlot());

		if (!workingDays.contains(appointmentDay))
			return false;
		if (!appointmenTime.isBefore(doctorAvailability.getTimeSlot()))
			return false;

		// 2. check if the slot is available
		List<Appointment> currentAppointments = appointmentRepository.findByDoctorIdAndStatus(doctor.getId(),
				"Scheduled");
		for (Appointment appointment : currentAppointments) {
			if (appointment.getAppointmentDatetime().isEqual(appointmentDateTime))
				return false;
		}
		return true;
	}

	// ... (other methods)

}
