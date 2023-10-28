package com.krish.meditrack.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

	public int getId() {
		return id;
	}

	public Appointment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointment(int id, int patientId, int doctorId, LocalDateTime appointmentDatetime, String status,
			String category, Timestamp appointmentTimestamp) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.appointmentDatetime = appointmentDatetime;
		this.status = status;
		this.category = category;
		this.appointmentTimestamp = appointmentTimestamp;
	}

	public String getAppointmentTimestamp() {
//		return appointmentTimestamp; 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(appointmentTimestamp);
	}

	public void setAppointmentTimestamp(Timestamp appointmentTimestamp) {
		this.appointmentTimestamp = appointmentTimestamp;
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

	public String getcategory() {
		return category;
	}

	public void setcategory(String category) {
		this.category = category;
	}

}
