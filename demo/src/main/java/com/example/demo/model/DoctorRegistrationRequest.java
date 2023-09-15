package com.example.demo.model;

import java.time.LocalTime;
import java.util.List;

public class DoctorRegistrationRequest {

	private Doctor doctor;
	private List<String> workingDays;
	private LocalTime timeSlot;

	public LocalTime getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(LocalTime timeSlot) {
		this.timeSlot = timeSlot;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<String> getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(List<String> workingDays) {
		this.workingDays = workingDays;
	}

	public DoctorRegistrationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorRegistrationRequest(Doctor doctor, List<String> workingDays, LocalTime timeSlot) {
		super();
		this.doctor = doctor;
		this.workingDays = workingDays;
		this.timeSlot = timeSlot;
	}

}
