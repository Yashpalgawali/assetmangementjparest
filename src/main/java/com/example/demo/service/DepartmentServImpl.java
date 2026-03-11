package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.GlobalException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Activity;
import com.example.demo.models.Company;
import com.example.demo.models.Department;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.DepartmentRepo;

import lombok.RequiredArgsConstructor;

@Service("deptserv")
@RequiredArgsConstructor
public class DepartmentServImpl implements DepartmentService {

	private final DepartmentRepo deptrepo;

	private final ActivityRepo activityrepo;

	private final CompanyService compserv;

	DateTimeFormatter tday = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter ttime = DateTimeFormatter.ofPattern("hh:mm:ss");

	@Override
	public Department saveDepartment(Department dept) {
		Company companyObj = compserv.getCompanyById(dept.getCompany().getComp_id());
		dept.setCompany(companyObj);
		Department depart = deptrepo.save(dept);
		Activity activity = null;
		if (depart != null) {
			activity = new Activity();
			activity.setActivity(depart.getDept_name() + " is saved successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return depart;
		} else {
			activity = new Activity();
			activity.setActivity(dept.getDept_name() + " is not saved ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);

			throw new GlobalException("Department " + dept.getDept_name() + " is not saved");
		}
	}

	@Override
	public List<Department> getAllDepartments() {
		return deptrepo.findAll();
	}

	@Override
	public Department getDepartmentById(Long deptid) {

		return deptrepo.findById(deptid)
				.orElseThrow(() -> new ResourceNotFoundException("No Department found for given ID " + deptid));
	}

	@Override
	public int updateDepartment(Department dept) {
		Company company = compserv.getCompanyById(dept.getCompany().getComp_id());
		dept.setCompany(company);
		int res = deptrepo.updateDepartmentById(dept.getDept_name(), dept.getCompany().getComp_id(), dept.getDept_id());
		Activity activity = null;
		if (res > 0) {
			activity = new Activity();
			activity.setActivity(dept.getDept_name() + " is updated successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			
			return res;
		} else {
			activity = new Activity();
			activity.setActivity("Department "+dept.getDept_name() + " is not updated ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			
			throw new GlobalException("Department "+dept.getDept_name()+" is not updated ");
		}
		
	}

	@Override
	public List<Department> getDepartmentByCompanyId(Long cid) {
		List<Department> deptlist = deptrepo.getAllDepartmentsByCompanyId(cid);
		if (deptlist.size() > 0) {
			return deptlist;
		} else {
			throw new ResourceNotFoundException("No Departments found in the Company");
		}
	}

	@Override
	public List<Department> getDepartmentByCompanyName(String cname) {
		List<Department> deptList = deptrepo.getAllDepartmentsByCompanyName(cname);
		if (deptList.size() > 0) {
			return deptList;
		} else {
			throw new ResourceNotFoundException("No Departments found for the company " + cname);
		}
	}

}
