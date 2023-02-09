package com.app.hospital.controller;

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
import com.app.hospital.dto.AdminDTO;
import com.app.hospital.exceptions.AdminNotFoundException;
import com.app.hospital.service.AdminService;


@RestController
@RequestMapping("/api/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	//mapping the save method of admin from admin Service -Post Mapping

	@PostMapping
	
	public ResponseEntity<AdminDTO> saveAdmin(@Valid @RequestBody AdminDTO adminDTO) {

		AdminDTO admin = adminService.saveAdmin(adminDTO);

		return ResponseEntity.ok(admin);
	}

	//mapping the edit method of admin from admin Service -Put Mapping
	@PutMapping
	public ResponseEntity<AdminDTO> updateAdmin(@Valid @RequestBody AdminDTO dto) {

		return new ResponseEntity<AdminDTO>(adminService.updateAdmin(dto), HttpStatus.ACCEPTED);
	}
	
	//mapping the delete method of admin from admin Service-Delete Mapping

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAdmin(@PathVariable int id) {
		AdminDTO dto = adminService.getById(id);
		if (dto != null) {
			return new ResponseEntity<Boolean>(adminService.deleteAdmin(dto), HttpStatus.OK);
		}
		throw new AdminNotFoundException("The  AdminId" + id + "does not exists");
	}
	
	// mapping the getById method of admin from admin Service -Get Mapping

	@GetMapping("/{adminId}")
	public ResponseEntity<?> getById(@PathVariable Integer adminId) {
		AdminDTO dto = adminService.getById(adminId);
		if(dto !=null) {
			return new ResponseEntity<AdminDTO>(dto,HttpStatus.FOUND);
		}
		throw new AdminNotFoundException("The Admin id"+ adminId +"does not exists");
	
	}

	
}
