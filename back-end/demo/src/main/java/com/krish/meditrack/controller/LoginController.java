package com.krish.meditrack.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krish.meditrack.model.Doctor;
import com.krish.meditrack.model.Patient;
import com.krish.meditrack.model.PersonalAssistant;
import com.krish.meditrack.model.User;
import com.krish.meditrack.service.DoctorService;
import com.krish.meditrack.service.PatientService;
import com.krish.meditrack.service.PersonalAssistantService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PersonalAssistantService personalAssistantService;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		if (user.userType.equals("patient")) {
			Optional<Patient> patient = patientService.login(user.getEmail(), user.getPassword());
			if (patient.isPresent()) {
				return new ResponseEntity<>(patient.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>("invalid credentials", HttpStatus.UNAUTHORIZED); // Authentication failed
			}
		}

		else if (user.userType.equals("doctor")) {
			Optional<Doctor> doctor = doctorService.login(user.getEmail(), user.getPassword());
			if (doctor.isPresent()) {
				return new ResponseEntity<>(doctor.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Authentication failed
			}
		}

		else if (user.userType.equals("personal assistant")) {
			Optional<PersonalAssistant> personalAssistant = personalAssistantService
					.loginPersonalAssistant(user.getEmail(), user.getPassword());
			if (personalAssistant.isPresent()) {
				return new ResponseEntity<>(personalAssistant.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Authentication failed
			}
		}

		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
