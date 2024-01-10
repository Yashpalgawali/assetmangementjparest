package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Activity;

public interface ActivityService {

	public Activity saveActivity(Activity activity);
	
	public List<Activity> getAllActivities();
}
