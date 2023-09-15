package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "patient_id")
	private Long patientId;

	@Column(name = "doctor_id")
	private Long doctorId;

	@Column(name = "receptionist_id")
	private Long receptionistId;

	@Column(name = "appointment_datetime")
	private LocalDateTime appointmentDatetime;

	@Column(name = "status")
	private String status;

	@Column(name = "comments")
	private String comments;

	// future scope
//	@Column(name = "time_slot")
//	private LocalTime timeSlot;

	public Appointment(Long id, Long patientId, Long doctorId, Long receptionistId, LocalDateTime appointmentDatetime,
			String status, String comments) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.receptionistId = receptionistId;
		this.appointmentDatetime = appointmentDatetime;
		this.status = status;
		this.comments = comments;
	}

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getReceptionistId() {
		return receptionistId;
	}

	public void setReceptionistId(Long receptionistId) {
		this.receptionistId = receptionistId;
	}

	public LocalDateTime getAppointmentDatetime() {
		return appointmentDatetime;
	}

	public void setAppointmentDatetime(LocalDateTime appointmentDatetime) {
		this.appointmentDatetime = appointmentDatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
