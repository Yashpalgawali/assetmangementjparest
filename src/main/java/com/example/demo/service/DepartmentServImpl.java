package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Activity;
import com.example.demo.models.Department;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.DepartmentRepo;

@Service("deptserv")
public class DepartmentServImpl implements DepartmentService {

	@Autowired
	DepartmentRepo deptrepo;
	@Autowired
	ActivityRepo activityrepo;
	
	DateTimeFormatter tday  =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter ttime =  DateTimeFormatter.ofPattern("hh:mm:ss");
	
	Activity activity;
	
	@Override
	public Department saveDepartment(Department dept) {
		Department depart = deptrepo.save(dept);
		if(depart!=null) {
			activity=new Activity();
			activity.setActivity(depart.getDept_name() +" is saved successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return depart;
		}
		else {
			activity=new Activity();
			activity.setActivity(dept.getDept_name() +" is not saved ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return depart;
		}
	}

	@Override
	public List<Department> getAllDepartments() {
		return deptrepo.findAll();
	}

	@Override
	public Department getDepartmentById(Long deptid) {
		try {
			return  deptrepo.findById(deptid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateDepartment(Department dept) {
		return deptrepo.updateDepartmentById(dept.getDept_name(), dept.getCompany().getComp_id(), dept.getDept_id());
	}

	@Override
	public List<Department> getDepartmentByCompanyId(Long cid) {
		return deptrepo.getAllDepartmentsByCompanyId(cid);
	}

	@Override
	public List<Department> getDepartmentByCompanyName(String cname) {
		return deptrepo.getAllDepartmentsByCompanyName(cname);
	}

}
