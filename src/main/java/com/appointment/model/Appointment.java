package com.appointment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Appointment resource model with all the required resources and fields.
 * 
 * @author anand.prakash
 *
 */

public class Appointment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer appointmentId;

	private List<AppointmentSlot> appointmentSlot;


	private Customer customer;

	private AppointmentStatus status;

	private String reason;

	private String cancelReason;

	private AppointmentType appointmentType;
	
	public enum AppointmentStatusType {
		SCHEDULED, ACTIVE, WALKIN,CANCELLED, MISSED, COMPLETED
	}

	public enum AppointmentStatus {

		SCHEDULED("Scheduled", AppointmentStatusType.SCHEDULED),
        WALKIN("Walk-In", AppointmentStatusType.ACTIVE),
        CANCELLED("Cancelled", AppointmentStatusType.CANCELLED),
        MISSED("Missed", AppointmentStatusType.MISSED),
        COMPLETED("Completed", AppointmentStatusType.COMPLETED);

		private final String name;

		private final AppointmentStatusType type;

		private AppointmentStatus(final String name,
				final AppointmentStatusType type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return this.name;
		}

		public AppointmentStatusType getType() {
			return this.type;
		}

		@Override
		public String toString() {
			return name;
		}

		public static List<AppointmentStatus> getAppointmentsStatusByTypes(
				List<AppointmentStatusType> appointmentStatusTypes) {
			List<AppointmentStatus> appointmentStatuses = new ArrayList<AppointmentStatus>();

			for (AppointmentStatus appointmentStatus : AppointmentStatus
					.values()) {
				if (appointmentStatusTypes
						.contains(appointmentStatus.getType())) {
					appointmentStatuses.add(appointmentStatus);
				}
			}

			return appointmentStatuses;
		}


		public static List<AppointmentStatus> getNotCancelledAppointmentStatuses() {
			return getAppointmentsStatusByTypes(Arrays.asList(
					AppointmentStatusType.ACTIVE,
					AppointmentStatusType.COMPLETED,
					AppointmentStatusType.MISSED,
					AppointmentStatusType.SCHEDULED));
		}

	}


	public Appointment() {

	}

	public Appointment(Integer appointmentId) {
		setId(appointmentId);
	}

	public Appointment(List<AppointmentSlot> appointmentSlot, Customer customer,
			AppointmentType appointmentType, AppointmentStatus status) {
		setAppointmentSlot(appointmentSlot);
		setCustomer(customer);
		setStatus(status);
		setAppointmentType(appointmentType);
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}


	public Integer getId() {
		return getAppointmentId();
	}


	public void setId(Integer id) {
		setAppointmentId(id);
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public List<AppointmentSlot> getAppointmentSlot() {
		return appointmentSlot;
	}

	public void setAppointmentSlot(List<AppointmentSlot> appointmentSlot) {
		this.appointmentSlot = appointmentSlot;
	}
	
	

}
