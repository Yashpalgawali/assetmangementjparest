package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.models.Activity;
import com.example.demo.repository.ActivityRepo;

@Service("activityserv")
public class ActivityServImpl implements ActivityService {

	private final ActivityRepo activityrepo;
	
	public ActivityServImpl(ActivityRepo activityrepo) {
		super();
		this.activityrepo = activityrepo;
	}

	@Override
	public Activity saveActivity(Activity activity) {
		return activityrepo.save(activity);
	}

	@Override
	public List<Activity> getAllActivities() {
		return activityrepo.findAll();
	}

}
