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
	
//	@GetMapping("/addcompany")
//	public String addCompany()
//	{
//		return "AddCompany";
//	}
	
	@PostMapping("/")
	public ResponseEntity<List<Company>> saveCompany(@RequestBody Company company)
	{
		Company comp = compserv.saveCompany(company);
		if(comp!=null)
		{
			return new ResponseEntity<List<Company>>(compserv.getAllCompanies(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Company>>  viewCompany()
	{
			List<Company> clist = compserv.getAllCompanies();
			if(clist.size()>0){
				return new ResponseEntity<List<Company>>(clist,HttpStatus.OK) ;
			}
			else {
				return new ResponseEntity<List<Company>>(clist,HttpStatus.NOT_FOUND) ;
			}
	}
	
//	@GetMapping("/editcompany/{id}")
//	public String editCompany(@PathVariable("id") Long cid,Model model , RedirectAttributes attr)
//	{
//		Company comp = compserv.getCompanyById(""+cid);
//		model.addAttribute("comp", comp);
//		return "EditCompany";
//	}
//	
//	@RequestMapping("/updatecompany")
//	public String updateCompany(@ModelAttribute("Company")Company comp,RedirectAttributes attr)
//	{
//		int res = compserv.updateCompany(comp);
//		if(res > 0)
//		{
//			attr.addFlashAttribute("response", "Company updated successfully");
//			return "redirect:/viewcompanies";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Company is not updated ");
//			return "redirect:/viewcompanies";
//		}
//		
//	}


}
