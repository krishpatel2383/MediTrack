package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PersonalAssistant;

@Repository
public interface PersonalAssistantRepository extends JpaRepository<PersonalAssistant, Integer> {
	// Define custom queries if needed
}
