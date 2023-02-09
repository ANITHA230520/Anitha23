package com.app.hospital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.hospital.dto.DoctorDTO;
import com.app.hospital.exceptions.DoctorNotFoundException;
import com.app.hospital.service.DoctorService;


@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	// Mapping add method from doctor Service
	
	@PostMapping
	public ResponseEntity<DoctorDTO> saveDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
		
		DoctorDTO doctor = doctorService.saveDoctor(doctorDTO);
		
		return  ResponseEntity.ok(doctor);
	}
	
	// Mapping edit method from Doctor Service
	
	@PutMapping
	public ResponseEntity<DoctorDTO> updateDoctor(@Valid@RequestBody DoctorDTO dto) {
		
		return new ResponseEntity<DoctorDTO>(doctorService.updateDoctor(dto),HttpStatus.ACCEPTED);
	}
	
	// Mapping delete method from doctor Service  and throwing exception if id is not found
	
	@DeleteMapping("/{doctorId}")
	public ResponseEntity<?> deleteDoctor(@PathVariable Integer doctorId) {
		DoctorDTO dto = doctorService.getById(doctorId);
		if(dto !=null) {
			return new ResponseEntity<Boolean>(doctorService.deleteDoctor(dto),HttpStatus.OK);
		}
		throw new DoctorNotFoundException("The doctor id"+doctorId  +"does not exists");
	}
	
	
	//Mapping getById method from doctor Service and throwing exception if id is not found
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<?> getDoctorById(@PathVariable Integer doctorId) {
		DoctorDTO dto = doctorService.getById(doctorId);
		if(dto !=null) {
			return new ResponseEntity<DoctorDTO>(dto,HttpStatus.FOUND);
		}
		throw new DoctorNotFoundException("The doctor id"+ doctorId +"does not exists");
	
	}
	
	// Mapping get all Doctor details from Doctor Service 
	
	@GetMapping
	public ResponseEntity<List<DoctorDTO>> getAllDoctor() {
		List<DoctorDTO> list = doctorService.findAll();
		return new ResponseEntity<List<DoctorDTO>>(list,HttpStatus.FOUND);
		
	}
}
