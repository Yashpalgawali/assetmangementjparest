package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.Company;
import com.example.demo.service.CompanyService;

@RestController
@CrossOrigin("*")
@RequestMapping("company")
public class CompanyController {

	@Autowired
	CompanyService compserv;
	

	@PostMapping("/")
	public ResponseEntity<List<Company>> saveCompany(@RequestBody Company company)
	{
		Company comp = compserv.saveCompany(company);
		if(comp!=null){
			return new ResponseEntity<List<Company>>(compserv.getAllCompanies(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Company>> viewCompany() {
			List<Company> clist = compserv.getAllCompanies();
			if(clist.size()>0){
				return new ResponseEntity<List<Company>>(clist,HttpStatus.OK) ;
			}
			else {
				return new ResponseEntity<List<Company>>(clist,HttpStatus.NOT_FOUND) ;
			}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> editCompany(@PathVariable("id") Long cid) {
		Company comp = compserv.getCompanyById(cid);
		if(comp!=null){
			return new ResponseEntity<Company>(comp,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/updatecompany")
	public ResponseEntity<List<Company>> updateCompany(@RequestBody Company comp){
		System.err.println("inside update comp controller \n Company = "+comp.toString());
		int res = compserv.updateCompany(comp);
		if (res > 0){
			return new ResponseEntity<List<Company>>(compserv.getAllCompanies(),HttpStatus.OK) ;
		}
		else {
			return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND) ;
		}
	}

}
