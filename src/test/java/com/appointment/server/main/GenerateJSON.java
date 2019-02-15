package com.appointment.server.main;

import com.appointment.model.Appointment;
import com.appointment.model.AppointmentType;
import com.appointment.model.Customer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class to generate input JSON string to make a GET appointment 
 * availability call with request body.
 * 
 * @author anand.prakash
 *
 */
public class GenerateJSON {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		
		Appointment apt = new Appointment();
		apt.setAppointmentId(1);
		
		Customer customer = new Customer();
		customer.setCustomerName("Anand");
		apt.setCustomer(customer);
		
		AppointmentType aptType = new AppointmentType();
		aptType.setAppointmentTypeId("1");
		//"Tier1:Maximize tax deductions and credits" - 45 mins
		aptType.setAppointmentServiceType("TIER1:Maximize tax deductions and credits");
		aptType.setDuration(45);	
		apt.setAppointmentType(aptType);
		
		try {
			String jsonInString = mapper.writeValueAsString(apt);
			
			System.out.println(jsonInString);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}