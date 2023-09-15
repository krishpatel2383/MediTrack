package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.DoctorAvailability;

public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Integer> {

//	int findByDoctor(doctor_id);
	@Query("SELECT da FROM DoctorAvailability da WHERE da.doctor.id = :doctorId")
	DoctorAvailability findByDoctorId(@Param("doctorId") Integer doctorId);
}
