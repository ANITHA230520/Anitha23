package com.app.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.hospital.dto.FeedBackDTO;
import com.app.hospital.entity.FeedBack;
import com.app.hospital.service.FeedBackService;

@RestController
@RequestMapping("/api/feedBacks")

public class FeedBackController {

	@Autowired
	private FeedBackService feedBackService;
	
	// Mapping the add feedback details from Feedback Service 

	@PostMapping("/feedBack")
	public FeedBack saveFeedBack(@RequestBody FeedBackDTO feedBackDTO) {
	FeedBack feedBack=feedBackService.saveFeedBack(feedBackDTO);
	return feedBack;
	}

}
