package com.krish.meditrack.model;

import java.sql.Timestamp;
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
	private int id;

	@Column(name = "patient_id")
	private int patientId;

	@Column(name = "doctor_id")
	private int doctorId;

	@Column(name = "appointment_datetime")
	private LocalDateTime appointmentDatetime;

	@Column(name = "status")
	private String status;

	@Column(name = "category")
	private String category;

	@Column(name = "appointment_timestamp")
	private Timestamp appointmentTimestamp;

	@Column(name = "description")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Timestamp getAppointmentTimestamp() {
		return appointmentTimestamp;
	}

	public void setAppointmentTimestamp(Timestamp appointmentTimestamp) {
		this.appointmentTimestamp = appointmentTimestamp;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", appointmentDatetime=" + appointmentDatetime + ", status=" + status + ", category=" + category
				+ ", appointmentTimestamp=" + appointmentTimestamp + ", description=" + description + "]";
	}

	public Appointment(int id, int patientId, int doctorId, LocalDateTime appointmentDatetime, String status,
			String category, Timestamp appointmentTimestamp, String description) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentDatetime = appointmentDatetime;
		this.status = status;
		this.category = category;
		this.appointmentTimestamp = appointmentTimestamp;
		this.description = description;
	}

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

}
