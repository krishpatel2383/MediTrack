package com.example.demo.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_availability")
public class DoctorAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "doctor_id", referencedColumnName = "id")
	private Doctor doctor;

	@Column(name = "time_slot", columnDefinition = "DATETIME", nullable = false)
	private LocalTime timeSlot;

	@Column(name = "working_days", columnDefinition = "JSON", nullable = false)
	private String workingDays;

	public DoctorAvailability() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorAvailability(int id, Doctor doctor, LocalTime timeSlot, String workingDays) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.timeSlot = timeSlot;
		this.workingDays = workingDays;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalTime getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(LocalTime timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

}
