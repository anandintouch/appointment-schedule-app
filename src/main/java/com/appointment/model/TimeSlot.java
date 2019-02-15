package com.appointment.model;



public class TimeSlot {

	private Integer timeSlotId;
	
	
	private String startTime;
	
	private String endTime;
	
	public Integer getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(Integer timeSlotId) {
		this.timeSlotId = timeSlotId;
	}



	public String getStartDate() {
		return startTime;
	}

	public void setStartDate(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endTime;
	}

	public void setEndDate(String endTime) {
		this.endTime = endTime;
	}



}
