package com.krish.meditrack.model;

import java.time.LocalTime;
import java.util.List;

public class DoctorRegistrationRequest {

	private Doctor doctor;
	private List<String> workingDays;
	private LocalTime startTime;
	private LocalTime endTime;

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
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

	public DoctorRegistrationRequest(Doctor doctor, List<String> workingDays, LocalTime startTime, LocalTime endTime) {
		super();
		this.doctor = doctor;
		this.workingDays = workingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

}
