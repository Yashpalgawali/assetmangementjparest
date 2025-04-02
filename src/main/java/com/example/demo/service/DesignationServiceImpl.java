package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.NoContentException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Activity;
import com.example.demo.models.Designation;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.DesignationRepo;

@Service("desigserv")
public class DesignationServiceImpl implements DesignationService {

	private final DesignationRepo desigrepo;
	private final ActivityRepo activityrepo;

	public DesignationServiceImpl(DesignationRepo desigrepo, ActivityRepo activityrepo) {
		super();
		this.desigrepo = desigrepo;
		this.activityrepo = activityrepo;
	}

	DateTimeFormatter tday = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter ttime = DateTimeFormatter.ofPattern("hh:mm:ss");

	Activity activity = null;

	@Override
	public Designation saveDesignation(Designation desig) {
		Designation des = desigrepo.save(desig);
		if (des != null) {
			activity = new Activity();
			activity.setActivity(des.getDesig_name() + " is saved successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return des;
		} else {
			activity = new Activity();
			activity.setActivity(desig.getDesig_name() + " is not saved ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return des;
		}
	}

	@Override
	public List<Designation> getAllDesignations() throws NoContentException {
		var desigList = desigrepo.findAll();
		if(desigList.size()>0)
			return desigList;
		else 
			throw new NoContentException("No Designations found");
	}

	@Override
	public Designation getDesignationById(Long desigid) {

		return desigrepo.findById(desigid)
				.orElseThrow(() -> new ResourceNotFoundException("No Designation found for given ID " + desigid));
	}

	@Override
	public int updateDesignation(Designation desig) {
		int res = desigrepo.updateDesignation(desig.getDesig_id(), desig.getDesig_name());
		if (res > 0) {
			activity = new Activity();
			activity.setActivity(desig.getDesig_name() + " is updated successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		} else {
			activity = new Activity();
			activity.setActivity(desig.getDesig_name() + " is not updated ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		}
	}

}
