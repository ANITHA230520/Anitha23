package com.app.hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.hospital.dto.DoctorDTO;
import com.app.hospital.dto.PatientDTO;
import com.app.hospital.service.DoctorService;
import com.app.hospital.service.PatientService;

@SpringBootTest
public class CovidAppointmentAppTesting {
	@Autowired
	private DoctorService doctorService;

	@Autowired
	private PatientService patientService;
	

	@Test
	void testfindAll() {

		List<DoctorDTO> dlist = doctorService.findAll();
		assertEquals(6, dlist.size());

	}
	 @Test
		void testgetById() {
			PatientDTO p= patientService.getById(5);
			assertEquals("B+",p.getBloodGroup());
			assertEquals("Chennai",p.getCity());
			assertEquals("arjunvenus2@gmail.com",p.getEmail());
			assertEquals("Male",p.getGender());
			assertEquals(4,p.getHeight());
			assertEquals("venus23",p.getPassword());
			assertEquals(21,p.getPatientAge());
			assertEquals("Arjun",p.getPatientName());
		    assertEquals(9789956428L,p.getPhoneNo());
		    assertEquals(104,p.getTemperature());
		    assertEquals(50,p.getWeight());
	    }
	 @Test 
	 void testDoctorById(){
		 DoctorDTO d=doctorService.getById(4);
		 assertEquals("Coimbatore",d.getCity());
		 assertEquals("TanilSelvi",d.getDoctorName());
		 assertEquals("tamil23selvi@gmail.com",d.getDoctormail());
		 assertEquals(7305301542L,d.getDoctorphoneNo());
		 assertEquals(10,d.getExperience());
		 assertEquals("Neurologist",d.getSpecialization());
		 
	 }
}
    
