package com.appointment.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class AppointmentSlot {
	private List<TimeSlot> timeSlot;
	
	private Integer appointmentSlotId;
	
	//start_date="2005-01-03 00:00:00.0" 
	private String startDate;
	
	//end_date="2005-01-03 00:30:00.0"
	private String endDate;
	
	// Tax preparer name
	private TaxAgent taxAgent;
	
	private  Integer numberOfTaxAgents = 10;
	
	private Set<AppointmentType> types;
	
	public Integer getAppointmentSlotId() {
		return appointmentSlotId;
	}

	public void setAppointmentSlotId(Integer appointmentSlotId) {
		this.appointmentSlotId = appointmentSlotId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public TaxAgent getTaxAgent() {
		return taxAgent;
	}

	public void setTaxAgent(TaxAgent taxAgent) {
		this.taxAgent = taxAgent;
	}

	public Set<AppointmentType> getTypes() {
		return types;
	}

	public void setTypes(Set<AppointmentType> types) {
		this.types = types;
	}

	public Integer getNumberOfTaxAgents() {
		return numberOfTaxAgents;
	}

	public void setNumberOfTaxAgents(Integer numberOfTaxAgents) {
		this.numberOfTaxAgents = numberOfTaxAgents;
	}

	public List<TimeSlot> getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(List<TimeSlot> timeSlot) {
		this.timeSlot = timeSlot;
	}
	

}
