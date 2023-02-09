package com.app.hospital.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.hospital.dto.ScheduleDTO;
import com.app.hospital.entity.Doctor;
import com.app.hospital.entity.Schedule;
import com.app.hospital.exceptions.ScheduleNotFoundException;
import com.app.hospital.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	// Mapping add method from schedule Service
	@PostMapping("/schedule")
	public Schedule saveSchedule(@RequestBody ScheduleDTO scheduleDTO) {
		Schedule schedule = scheduleService.saveSchedule(scheduleDTO);
		return schedule;
	}

	// Mapping edit method from schedule Service
	@PutMapping
	public ResponseEntity<Schedule> updateSchedule(@Valid @RequestBody ScheduleDTO dto) {

		return new ResponseEntity<Schedule>(scheduleService.updateSchedule(dto), HttpStatus.ACCEPTED);
	}

    // Mapping get doctor by schedule Id from schedule Service
	@GetMapping("/{scheduleId}")
	public ResponseEntity<Doctor> getDoctorByScheduleId(@PathVariable Integer scheduleId) {
		Doctor doctor = scheduleService.getDoctorByScheduleId(scheduleId);
		if (doctor != null) {
			return new ResponseEntity<Doctor>(doctor, HttpStatus.FOUND);
		}
		throw new ScheduleNotFoundException("The Schedule id" + scheduleId + "does not exists");

	}
    //Mapping get all schedules from schedule Service
	@GetMapping
	public ResponseEntity<List<ScheduleDTO>> findAll() {
		List<ScheduleDTO> list = scheduleService.findAll();
		return new ResponseEntity<List<ScheduleDTO>>(list, HttpStatus.FOUND);
	}
	 

}
