package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.models.Activity;
import com.example.demo.repository.ActivityRepo;

@Service("activityserv")
public class ActivityServImpl implements ActivityService {

	@Autowired
	ActivityRepo activityrepo;
	
	@Override
	public Activity saveActivity(Activity activity) {
		return activityrepo.save(activity);
	}

	@Override
	public List<Activity> getAllActivities() {
		return activityrepo.findAll();
	}

}
