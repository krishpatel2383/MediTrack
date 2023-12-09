package com.krish.meditrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krish.meditrack.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	Optional<Doctor> findByEmail(String email);
}
