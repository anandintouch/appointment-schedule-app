package com.appointment.model;

public class Customer {
	
	String customerName;
	Integer totalAppointment =0;

	public Integer getTotalAppointment() {
		return totalAppointment;
	}

	public void setTotalAppointment(Integer totalAppointment) {
		this.totalAppointment = totalAppointment;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
