package com.krish.meditrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krish.meditrack.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Optional<Patient> findByEmail(String email);
}