package com.app.hospital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.hospital.dto.AppointmentDTO;
import com.app.hospital.entity.Appointment;
import com.app.hospital.service.AppointmentService;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	// mapping save method of appointment Details from Appointment Service-Post Mapping
	@PostMapping
	public Appointment saveAppointments(@RequestBody AppointmentDTO appointmentDTO) {
		Appointment appointment=appointmentService.saveAppoinments(appointmentDTO);
		return appointment;
		}
		
	// mapping edit method of appointment Details from Appointment Service-Put Mapping
	@PutMapping
	public ResponseEntity<Appointment> updateAppointment(@Valid@RequestBody AppointmentDTO dto) {
		
		return new ResponseEntity<Appointment>(appointmentService.updateAppointment(dto),HttpStatus.ACCEPTED);
	}
	
	//mapping Find all Appointments method of appointment Details from Appointment Service-Get Mapping
	@GetMapping
	public ResponseEntity<List<AppointmentDTO>> findAll() {
		List<AppointmentDTO> list = appointmentService.findAll();
		return new ResponseEntity<List<AppointmentDTO>>(list, HttpStatus.FOUND);
	}
}

