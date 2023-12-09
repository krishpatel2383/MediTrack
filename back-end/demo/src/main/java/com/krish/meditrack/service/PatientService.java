package com.krish.meditrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krish.meditrack.model.Patient;
import com.krish.meditrack.repository.AppointmentRepository;
import com.krish.meditrack.repository.PatientRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Transactional(readOnly = true)
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Patient> getPatientById(int patientId) {
		return patientRepository.findById(patientId);
	}

	@Transactional
	public Patient updatePatient(Patient updatedPatient) {
		Optional<Patient> currentPatient = patientRepository.findById(updatedPatient.getId());
		if (currentPatient.isEmpty()) {
			throw new EntityNotFoundException("patient does not exist");
		}
		Patient patient = currentPatient.get();
		patient.setEmail(updatedPatient.getEmail());
		patient.setAddress(updatedPatient.getAddress());
		patient.setName(updatedPatient.getName());
		patient.setPassword(updatedPatient.getPassword());
		patient.setPhone(updatedPatient.getPhone());

		return patientRepository.save(updatedPatient);
	}

	@Transactional
	public void deletePatient(int patientId) {
		try {
			appointmentRepository.deleteAllByPatientId(patientId);
			patientRepository.deleteById(patientId);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException("Patient with ID " + patientId + " not found");
		}
	}

	@Transactional
	public Patient registerPatient(Patient patient) {
		Optional<Patient> existingpatient = patientRepository.findByEmail(patient.getEmail());
		if (existingpatient.isPresent()) {
			throw new EntityExistsException("patient with this email already exist.");
		}
		return patientRepository.save(patient);
	}

	@Transactional
	public Optional<Patient> login(String email, String password) {
		Optional<Patient> optionalPatient = patientRepository.findByEmail(email);
		if (optionalPatient.isPresent()) {
			Patient patient = optionalPatient.get();
			if (patient.getPassword().equals(password)) {
				return Optional.of(patient);
			}
		}

		return Optional.empty();
	}

//	@Transactional
//	public Appointment requestAppointment(Appointment appointment) throws NotFoundException {
//		Doctor doctor = doctorRepository.findById(appointment.getDoctorId()).orElseThrow(() -> new NotFoundException());
//
//		if (!isAppointmentSlotAvailable(doctor, appointment.getAppointmentDatetime())) {
//			throw new IllegalArgumentException("time slot unavailable.");
//		}
//
//		Appointment appointment2 = new Appointment();
//		appointment2.setPatientId(appointment.getPatientId());
//		appointment2.setDoctorId(appointment.getDoctorId());
//		appointment2.setAppointmentDatetime(appointment.getAppointmentDatetime());
//		appointment2.setStatus("Scheduled");
//		appointment2.setCategory(appointment.getCategory());
//		appointment2.setAppointmentTimestamp(new Timestamp(System.currentTimeMillis()));
//		appointmentRepository.save(appointment2);
//
//		return appointment2;
//	}

//	private boolean isAppointmentSlotAvailable(Doctor doctor, LocalDateTime appointmentDateTime) {
//		// 1. check if the doctor is available for the slot
//		LocalDate appointmentDate = appointmentDateTime.toLocalDate();
//		LocalTime appointmenTime = appointmentDateTime.toLocalTime();
//
//		// convert date to day of week
//		String appointmentDay = appointmentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
//
//		DoctorAvailability doctorAvailability = doctorAvailabilityRepository.findByDoctorId(doctor.getId());
//
//		System.out.println("requested day:" + appointmentDay);
//		System.out.println("requested time:" + appointmenTime);
//
//		String workingDays = doctorAvailability.getWorkingDays();
//
//		System.out.println("available days:" + workingDays);
//		System.out.println(
//				"available time:" + doctorAvailability.getStartTime() + " to " + doctorAvailability.getEndTime());
//
//		if (!workingDays.contains(appointmentDay) || appointmentDateTime.isBefore(LocalDateTime.now()))
//			return false;
//		if (appointmenTime.isBefore(doctorAvailability.getStartTime())
//				|| appointmenTime.isAfter(doctorAvailability.getEndTime()))
//			return false;
//
//		// 2. check if the slot is available
//		List<Appointment> currentAppointments = appointmentRepository.findByDoctorIdAndStatus(doctor.getId(),
//				"Scheduled");
//		for (Appointment appointment : currentAppointments) {
//			if (appointment.getAppointmentDatetime().isEqual(appointmentDateTime))
//				return false;
//		}
//		return true;
//	}

}
