package com.krish.meditrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krish.meditrack.model.PersonalAssistant;

@Repository
public interface PersonalAssistantRepository extends JpaRepository<PersonalAssistant, Integer> {
	// Define custom queries if needed
	Optional<PersonalAssistant> findByEmail(String email);
}
