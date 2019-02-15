package com.appointment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appointment.model.Appointment;
import com.appointment.service.AppointmentService;


/**
 * Controller class with endpoints to retrieve and create an appointment
 * 
 * @author anand.prakash
 *
 */
@Controller
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	/**
	 * Method to get available appointments with appointment slots/time slots 
	 * and other needed details.
	 * 
	 * @param appointment
	 * @return list of appointment
	 */
	@RequestMapping(method = RequestMethod.GET, value="api/v1/appointments")
	@ResponseBody
	public List<Appointment> getAppointmentsAvailability(@RequestBody Appointment appointment) {
		List<Appointment> availableAppointments = null;
		
		try {
			System.out.println("Retrieving appointment schedules");
			
			availableAppointments = appointmentService.getAppointmentsAvailability(appointment);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return availableAppointments;
	}
	
	/**
	 * Method to create/save and schedule an appointment.
	 *  
	 * @param appointment
	 * @return appointment
	 */
	@RequestMapping(method = RequestMethod.POST, value="api/v1/appointments")
	@ResponseBody
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		
		System.out.println("Creating appointment");
		Appointment appointmentResult = null;           

        //Returning appointment object with some of the details to the caller
        if(appointment !=null){
        	
        	try {
				appointmentResult = appointmentService.createAppointment(appointment);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
  
        }

        return appointmentResult;
        
	}

}
