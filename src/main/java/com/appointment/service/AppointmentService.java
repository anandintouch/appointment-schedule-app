package com.appointment.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.appointment.model.Appointment;
import com.appointment.model.Appointment.AppointmentStatus;
import com.appointment.model.AppointmentSlot;
import com.appointment.model.AppointmentType;
import com.appointment.model.Customer;
import com.appointment.model.TaxAgent;
import com.appointment.model.TimeSlot;

/**
 * Service component which has business logic and creates response data.
 * This can be extended to make database call to retrieve and store data.
 * 
 * @author anand.prakash
 *
 */
@Component
public class AppointmentService {
	
	public enum AppointmentServiceType {
		TIER1, TIER2, TIER3
	} 
	
	public List<Appointment> getAppointmentsAvailability(Appointment appointment) throws Exception {
		
		if (appointment == null || appointment.getCustomer() == null || 
				appointment.getAppointmentType() == null) {
			throw new Exception("Appointment can not be null.");
		}
		
		List<Appointment> appointmentsList = getAppointmentsData(appointment);
		
		return appointmentsList;
		
	}

	
	public Appointment createAppointment(Appointment appointmentReq)
			throws Exception {
		Appointment appointment = null;
		
		return createAppointmentData(appointmentReq);
		
	}
	
	/**
	 * Method to create sample Appointment data with appointment slot and timeslots list composed in it.
	 * 
	 * @param appointment
	 * @return
	 */
	private List<Appointment> getAppointmentsData(Appointment appointment){
		List<Appointment> appointmentsList = new LinkedList<Appointment>();
		
		Appointment apt = new Appointment();
		
		apt.setAppointmentId(1);
		
		if(appointment.getCustomer().getCustomerName() != null){
			Customer customer = new Customer();
			customer.setCustomerName(appointment.getCustomer().getCustomerName());
			apt.setCustomer(customer);
		}
		if(appointment.getAppointmentType().getAppointmentServiceType() !=null){
			AppointmentType aptType = new AppointmentType();
			aptType.setAppointmentTypeId("1");
			//"Tier1:Maximize tax deductions and credits" - 45 mins
			aptType.setAppointmentServiceType(appointment.getAppointmentType().getAppointmentServiceType());
			aptType.setDuration(45);	
			apt.setAppointmentType(aptType);
		}
		
		//Create AppointmentSlots
		List<AppointmentSlot> appointmentSlots = new LinkedList<AppointmentSlot>();
		
		AppointmentSlot aptSlots1 = new AppointmentSlot();
		aptSlots1.setAppointmentSlotId(1);
		
		String startDate = DayOfWeek.of(1)+" "+LocalDate.now().atTime(8, 00);

		aptSlots1.setStartDate(startDate);

		String endDate= DayOfWeek.valueOf("SATURDAY")+" "+LocalDate.now().plusDays(5).atTime(17, 00);
		aptSlots1.setEndDate(endDate);
		
		TaxAgent taxAgent= new TaxAgent();
		taxAgent.setTaxAgentId(1);
		taxAgent.setAgentName("Frank");
		aptSlots1.setTaxAgent(taxAgent);
		
		aptSlots1.setTimeSlot(createTimeSlots());
				
		appointmentSlots.add(aptSlots1);
		
		apt.setAppointmentSlot(appointmentSlots);
		appointmentsList.add(apt);
		
		return appointmentsList;
		
	}
	
	/**
	 * Method to save/create one of the first selected appointment data.
	 * 
	 * @param appointment
	 * @return
	 */
	private Appointment createAppointmentData(Appointment appointment){
		
		Appointment appointmentResult = new Appointment();
		String appointmentServiceType = null ;
		
		appointmentResult.setAppointmentId(1);
		
		if(appointment.getCustomer().getCustomerName() != null){
			
			//Check if total number appointments of customer is 0 then set to 1
			if(appointment.getCustomer().getTotalAppointment() ==0){
				appointment.getCustomer().setTotalAppointment(1);
			}
			
			appointmentResult.setCustomer(appointment.getCustomer());
			
		}
		
		if(appointment.getAppointmentType() != null && appointment.getAppointmentType()
				.getAppointmentServiceType()!=null){

			//Set duration for specific service types
			String[] service =appointment.getAppointmentType().getAppointmentServiceType().split(":");
			
			if( service[0].equals(AppointmentServiceType.TIER1.toString())){
				appointmentServiceType = AppointmentServiceType.TIER1.toString();
				appointment.getAppointmentType().setDuration(45);
			} else if( service[0].equals(AppointmentServiceType.TIER2.toString())){
				appointmentServiceType = AppointmentServiceType.TIER2.toString();
				appointment.getAppointmentType().setDuration(60);
			} else{
				appointmentServiceType = AppointmentServiceType.TIER3.toString();
				appointment.getAppointmentType().setDuration(90);
			}
			
			appointmentResult.setAppointmentType(appointment.getAppointmentType());
		}
		
		List<AppointmentSlot> appointmentSlotsResult = new LinkedList<AppointmentSlot>();
		
		if(appointment.getAppointmentSlot() != null) {
			for(AppointmentSlot slots: appointment.getAppointmentSlot()){
				
				AppointmentSlot aptSlots1 = new AppointmentSlot();
				aptSlots1.setAppointmentSlotId(slots.getAppointmentSlotId());
				
				String[] startDateArray = slots.getStartDate().split(" ");
				LocalDateTime startDate = LocalDateTime.parse(startDateArray[1]);
				
				String[] endDateArray = slots.getEndDate().split(" ");
				LocalDateTime endDate = LocalDateTime.parse(endDateArray[1]);
				LocalDateTime limitDate = LocalDateTime.now().plusMonths(2);
				
				//Validate appointment end date is before 60 days;
				if(endDate.isBefore(limitDate)  ) {
					
					aptSlots1.setStartDate(slots.getStartDate());
					
					//Set end date by adding duration of appointment for specific Service Tier
					LocalDateTime addedDuration =startDate.plusMinutes(appointment.getAppointmentType().getDuration());
					aptSlots1.setEndDate(startDateArray[0]+" "+addedDuration);
					aptSlots1.setTaxAgent(slots.getTaxAgent());
					
					//One tax agent assigned ,so update number of agent left.
					int numOfAgents = slots.getNumberOfTaxAgents();
					if(numOfAgents >0 && numOfAgents <=10 ){
						aptSlots1.setNumberOfTaxAgents(numOfAgents-1);
						
					}
					
				}
				//Picking one of the time slot and setting- more logic can be introduced here
				if(slots.getTimeSlot().get(0) !=null){
					
					List<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
					
					TimeSlot timeSlot1 = new TimeSlot();
					String stDt = slots.getTimeSlot().get(0).getStartDate();
					String enDt = slots.getTimeSlot().get(0).getEndDate();
					
					timeSlot1.setStartDate(stDt);
					timeSlot1.setEndDate(enDt);
					timeSlot1.setTimeSlotId(slots.getTimeSlot().get(0).getTimeSlotId());
					
					timeSlots.add(timeSlot1);
					aptSlots1.setTimeSlot(timeSlots);
				}
				
				appointmentSlotsResult.add(aptSlots1);
			}
		}
		appointmentResult.setAppointmentSlot(appointmentSlotsResult);
		
		appointmentResult.setStatus(AppointmentStatus.SCHEDULED);
		
		return appointmentResult;
		
	}
	
	private List<TimeSlot> createTimeSlots(){
		List<TimeSlot> timeSlots = new LinkedList<TimeSlot>();
		
		TimeSlot timeSlot1 = new TimeSlot();
		timeSlot1.setTimeSlotId(1);
		timeSlot1.setStartDate(""+LocalTime.of(8,0));
		timeSlot1.setEndDate(""+LocalTime.of(8,0).plusHours(1));
		timeSlots.add(timeSlot1);
		
		TimeSlot timeSlot2 = new TimeSlot();
		timeSlot2.setTimeSlotId(2);
		timeSlot2.setStartDate(""+LocalTime.of(8,0).plusHours(1));
		timeSlot2.setEndDate(""+LocalTime.of(8,0).plusHours(2));
		timeSlots.add(timeSlot2);
		
		TimeSlot timeSlot3 = new TimeSlot();
		timeSlot3.setTimeSlotId(3);
		timeSlot3.setStartDate(""+LocalTime.of(8,0).plusHours(2));
		timeSlot3.setEndDate(""+LocalTime.of(8,0).plusHours(3));
		timeSlots.add(timeSlot3);
		
		TimeSlot timeSlot4 = new TimeSlot();
		timeSlot4.setTimeSlotId(4);
		timeSlot4.setStartDate(""+LocalTime.of(8,0).plusHours(3));
		timeSlot4.setEndDate(""+LocalTime.of(8,0).plusHours(4));
		timeSlots.add(timeSlot4);
		
		TimeSlot timeSlot5 = new TimeSlot();
		timeSlot5.setTimeSlotId(5);
		timeSlot5.setStartDate(""+LocalTime.of(8,0).plusHours(4));
		timeSlot5.setEndDate(""+LocalTime.of(8,0).plusHours(5));
		timeSlots.add(timeSlot5);

		TimeSlot timeSlot6 = new TimeSlot();
		timeSlot6.setTimeSlotId(6);
		timeSlot6.setStartDate(""+LocalTime.of(8,0).plusHours(5));
		timeSlot6.setEndDate(""+LocalTime.of(8,0).plusHours(6));
		timeSlots.add(timeSlot6);
		
		TimeSlot timeSlot7 = new TimeSlot();
		timeSlot7.setTimeSlotId(7);
		timeSlot7.setStartDate(""+LocalTime.of(8,0).plusHours(6));
		timeSlot7.setEndDate(""+LocalTime.of(8,0).plusHours(7));
		timeSlots.add(timeSlot7);
		
		TimeSlot timeSlot8 = new TimeSlot();
		timeSlot8.setTimeSlotId(8);
		timeSlot8.setStartDate(""+LocalTime.of(8,0).plusHours(7));
		timeSlot8.setEndDate(""+LocalTime.of(8,0).plusHours(8));
		timeSlots.add(timeSlot8);
		
		TimeSlot timeSlot9 = new TimeSlot();
		timeSlot9.setTimeSlotId(9);
		timeSlot9.setStartDate(""+LocalTime.of(8,0).plusHours(8));
		timeSlot9.setEndDate(""+LocalTime.of(8,0).plusHours(9));
		timeSlots.add(timeSlot9);
		
		return timeSlots;
		
	}
	
	public List<AppointmentSlot> getAppointmentBlocksByTypes(Date fromDate,
			Date toDate, String taxAgent,
			List<AppointmentType> appointmentTypes) {
		return getAppointmentBlocks(fromDate, toDate,
				 taxAgent, appointmentTypes);
	}
	
	private List<AppointmentSlot> getAppointmentBlocks(Date fromDate, Date toDate, String taxAgent,
	        List<AppointmentType> appointmentTypes) {
		List<AppointmentSlot> filteredAppointmentSlots = null;
		
		return filteredAppointmentSlots;		
		
		
	}
	
/*	public List<Appointment> getAppointmentsByConstraints(Date fromDate,
			Date toDate,  String taxAgent,
			AppointmentType type, Customer customer, AppointmentStatus status)
			throws Exception {

		if (status == null) {
			return getAppointmentsByConstraints(fromDate, toDate,taxAgent,
					 type, customer, new ArrayList<AppointmentStatus>());
		}

		return getAppointmentsByConstraints(fromDate, toDate,
				customer, type, customer, Arrays.asList(status));
	}*/
	
	/*public List<TimeSlot> getTimeSlotsByConstraints(AppointmentType appointmentType, Date fromDate, Date toDate, String taxAgent,  Customer excludeTimeSlotsWithPatient) throws Exception {

        List<TimeSlot> suitableTimeSlots = getTimeSlotsByConstraintsIncludingFull(
                appointmentType, fromDate, toDate, provider, location, excludeTimeSlotsWithPatient);

        List<TimeSlot> availableTimeSlots = new LinkedList<TimeSlot>();

        Integer duration = appointmentType.getDuration();
        for (TimeSlot slot : suitableTimeSlots) {

            // Filter by time left
            if (getTimeLeftInTimeSlot(slot) >= duration)
                availableTimeSlots.add(slot);
        }

        return availableTimeSlots;
    }*/
	
	
	/*
	 Date now = Calendar.getInstance().getTime();
	 
	 if (now.after(appointment.getTimeSlot().getEndDate())) 
	   
	 */
	   
/*	public Integer getTimeLeftInTimeSlot(TimeSlot timeSlot) {

		if (timeSlot == null) {
			return null;
		}

		Integer minutes = Minutes.minutesBetween(
				new DateTime(timeSlot.getStartDate()),
				new DateTime(timeSlot.getEndDate())).getMinutes();

		for (Appointment appointment : Context.getService(
				AppointmentService.class)
				.getAppointmentsInTimeSlotThatAreNotCancelled(timeSlot)) {
			minutes = minutes - appointment.getAppointmentType().getDuration();
		}

		return minutes;
	}*/

}
