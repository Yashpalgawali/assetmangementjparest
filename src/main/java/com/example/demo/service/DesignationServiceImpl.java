package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Activity;
import com.example.demo.models.Designation;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.DesignationRepo;

@Service("desigserv")
public class DesignationServiceImpl implements DesignationService {

	@Autowired
	DesignationRepo desigrepo;
	@Autowired
	ActivityRepo activityrepo;
	
	DateTimeFormatter tday  =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter ttime =  DateTimeFormatter.ofPattern("hh:mm:ss");
	
	Activity activity;
	
	@Override
	public Designation saveDesignation(Designation desig) {
		Designation des = desigrepo.save(desig);
		if(des!=null)
		{
			activity=new Activity();
			activity.setActivity(des.getDesig_name() +" is saved successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return des;
		}
		else
		{
			activity=new Activity();
			activity.setActivity(desig.getDesig_name() +" is not saved ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return des;
		}
	}

	@Override
	public List<Designation> getAllDesignations() {
		return desigrepo.findAll();
	}

	@Override
	public Designation getDesignationById(Long desigid) {
		try {
			return desigrepo.findById(desigid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateDesignation(Designation desig) {
		int res = desigrepo.updateDesignation(desig.getDesig_id(), desig.getDesig_name());
		if(res >0) {
			activity=new Activity();
			activity.setActivity(desig.getDesig_name() +" is updated successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		}	
		else {
			activity=new Activity();
			activity.setActivity(desig.getDesig_name() +" is not updated ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		}
	}

}
