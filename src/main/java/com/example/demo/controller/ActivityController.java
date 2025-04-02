package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Activity;
import com.example.demo.service.ActivityService;

@RestController
@RequestMapping("activity")
public class ActivityController {

	private final ActivityService activityserv;

	public ActivityController(ActivityService activityserv) {
		super();
		this.activityserv = activityserv;
	}

	@GetMapping("/")
	public ResponseEntity<List<Activity>> getAllActivities() {
		return new ResponseEntity<List<Activity>>(activityserv.getAllActivities(), HttpStatus.OK);
	}
}
