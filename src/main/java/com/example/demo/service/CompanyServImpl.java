package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Activity;
import com.example.demo.models.Company;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.CompanyRepo;

@Service("compserv")
public class CompanyServImpl implements CompanyService {

	private final CompanyRepo comprepo;

	private final ActivityRepo activityrepo;

	DateTimeFormatter tday = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter ttime = DateTimeFormatter.ofPattern("hh:mm:ss");

	public CompanyServImpl(CompanyRepo comprepo, ActivityRepo activityrepo) {
		super();
		this.comprepo = comprepo;
		this.activityrepo = activityrepo;
	}

	@Override
	public Company saveCompany(Company comp) {

		Company company = comprepo.save(comp);
		if (company != null) {
			Activity activity = new Activity();
			activity.setActivity(comp.getComp_name() + " is saved successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return company;
		} else {
			Activity activity = new Activity();
			activity.setActivity(comp.getComp_name() + " is not saved ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return company;
		}
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> clist = comprepo.findAll();
		if (clist.size() > 0) {
			return clist;
		} else {
			throw new ResourceNotFoundException("No Companies Are Found");
		}
	}

	@Override
	public Company getCompanyById(Long id) {
		Company comp = comprepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Company found for given ID " + id));
		return comp;
	}

	@Override
	public int updateCompany(Company comp) {

		int res = comprepo.updateCompany(comp.getComp_name(), comp.getComp_id());
		if (res > 0) {
			Activity activity = new Activity();
			activity.setActivity(comp.getComp_name() + " is updated successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		} else {
			Activity activity = new Activity();
			activity.setActivity(comp.getComp_name() + " is not updated ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		}
	}

}
