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

	@Column(name = "start_time", columnDefinition = "TIME", nullable = false)
	private LocalTime startTime;

	@Column(name = "end_time", columnDefinition = "TIME", nullable = false)
	private LocalTime endTime;

	@Column(name = "working_days", columnDefinition = "JSON", nullable = false)
	private String workingDays;

	public DoctorAvailability(int id, Doctor doctor, LocalTime startTime, LocalTime endTime, String workingDays) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.startTime = startTime;
		this.endTime = endTime;
		this.workingDays = workingDays;
	}

	public DoctorAvailability() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public String getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

}
