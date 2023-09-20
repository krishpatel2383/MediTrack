package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	List<Appointment> findByPatientId(int patientId);

	void deleteAllByPatientId(int patientId);

	List<Appointment> findByDoctorIdAndStatus(Integer doctorId, String status);
}
