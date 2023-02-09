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
import com.app.hospital.dto.PatientDTO;
import com.app.hospital.exceptions.PatientNotFoundException;
import com.app.hospital.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	// Mapping add method from patient Service
	@PostMapping
	public ResponseEntity<PatientDTO> savePatient(@Valid @RequestBody PatientDTO patientDTO) {
		
		PatientDTO patient = patientService.savePatient(patientDTO);
		
		return  ResponseEntity.ok(patient);
	}
	
	// Mapping edit  method from patient Service
	
	@PutMapping
	public ResponseEntity<PatientDTO> updatePatient(@Valid@RequestBody PatientDTO dto) {
		
		return new ResponseEntity<PatientDTO>(patientService.updatePatient(dto),HttpStatus.ACCEPTED);
	}
	
	// Mapping delete method from patient Service and throwing exception if id is not found
	
	@DeleteMapping("/{patientId}")
	public ResponseEntity<?> deletePatient(@PathVariable Integer patientId) {
		PatientDTO dto = patientService.getById(patientId);
		if(dto !=null) {
			return new ResponseEntity<Boolean>(patientService.deletePatient(dto),HttpStatus.OK);
		}
		throw new PatientNotFoundException("The Patient id"+patientId  +"does not exists");
	}
	
	//Mapping getById method from patient Service and throwing exception if id is not found
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {
		PatientDTO dto = patientService.getById(id);
		if(dto !=null) {
			return new ResponseEntity<PatientDTO>(dto,HttpStatus.FOUND);
		}
		throw new PatientNotFoundException("The patient id"+ id +"does not exists");
	
	}
	
	// Mapping get all patient details from Patient Service 
	
	@GetMapping
	public ResponseEntity<List<PatientDTO>> findAll() {
		List<PatientDTO> list = patientService.findAll();
		return new ResponseEntity<List<PatientDTO>>(list,HttpStatus.FOUND);
	}
	
	
	/*@GetMapping("/{date}")
	public ResponseEntity<List<PatientDTO>> findPatientByDate(@PathVariable LocalDate date){
		List<PatientDTO> list = patientService.findPatientByDate(date);
		return ResponseEntity.ok(list);
	
	}
	
	/*@GetMapping("doctor/{doctorId}")
	public ResponseEntity<List<PatientDTO>> findPatientByDoctorId(@PathVariable Integer doctorId){
		List<PatientDTO> list = patientService.findPatientByDoctorId(doctorId);
		return ResponseEntity.ok(list);
	
	}*/

}
