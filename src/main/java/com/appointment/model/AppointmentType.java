package com.appointment.model;

public class AppointmentType {
	
	private String appointmentTypeId;
	
	//Appointment Type is Tier1 ,Tier2 and Tier3 
	private String appointmentServiceType;
	
	//In minutes ,like  suration for 1 hr 30 min = 90 min
	private Integer duration;
	
	public String getAppointmentServiceType() {
		return appointmentServiceType;
	}

	public void setAppointmentServiceType(String appointmentServiceType) {
		this.appointmentServiceType = appointmentServiceType;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getAppointmentTypeId() {
		return appointmentTypeId;
	}

	public void setAppointmentTypeId(String appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}	

}
