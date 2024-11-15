package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Activity;
import com.example.demo.models.Company;
import com.example.demo.repository.ActivityRepo;
import com.example.demo.repository.CompanyRepo;

@Service("compserv")
public class CompanyServImpl implements CompanyService {

	private	CompanyRepo comprepo;
	
	private ActivityRepo activityrepo;
	
	DateTimeFormatter tday  =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
	DateTimeFormatter ttime =  DateTimeFormatter.ofPattern("hh:mm:ss");
	
	public CompanyServImpl(CompanyRepo comprepo, ActivityRepo activityrepo) {
		super();
		this.comprepo = comprepo;
		this.activityrepo = activityrepo;
	}

	Activity activity=null;
	
	@Override
	public Company saveCompany(Company comp) {
		
		Company company = comprepo.save(comp);
		if(company!=null)
		{
			activity=new Activity();
			activity.setActivity(comp.getComp_name()+" is saved successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return company;
		}else {
			activity=new Activity();
			activity.setActivity(comp.getComp_name()+" is not saved ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return company;
		}
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> clist = comprepo.getAllCompanies();
		return clist;
	}

	@Override
	public Company getCompanyById(Long id) {
		try {
			Company comp = comprepo.findById(id).get();
			return comp;
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateCompany(Company comp) {
		
		int res = comprepo.updateCompany(comp.getComp_name(), comp.getComp_id());
		if(res >0) {
			activity=new Activity();
			activity.setActivity(comp.getComp_name()+" is updated successfully");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		}	
		else {
			activity=new Activity();
			activity.setActivity(comp.getComp_name()+" is not updated ");
			activity.setOperation_date(tday.format(LocalDateTime.now()));
			activity.setOperation_time(ttime.format(LocalDateTime.now()));
			activityrepo.save(activity);
			return res;
		}
	}

}
