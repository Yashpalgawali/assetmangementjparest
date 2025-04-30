package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Company;
import com.example.demo.service.CompanyService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("company")
public class CompanyController {

	private final CompanyService compserv;

	public CompanyController(CompanyService compserv) {
		super();
		this.compserv = compserv;
	}

//	@ApiOperation("This End Point is used to save the Company")
//	@PostMapping("/")
//	public ResponseEntity<List<Company>> saveCompany(@RequestBody Company company)
//	{
//		Company comp = compserv.saveCompany(company);
//		if(comp!=null) {
//			return new ResponseEntity<List<Company>>(compserv.getAllCompanies(),HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<List<Company>>(HttpStatus.NO_CONTENT);
//		}
//	}
	
	@ApiOperation("This End Point is used to save the Company")
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveCompany(@RequestBody Company company)
	{
		Company comp = compserv.saveCompany(company);
		if(comp!=null) {
			 return ResponseEntity
					 .status(HttpStatus.OK)
					 .body(new ResponseDto(HttpStatus.OK.toString(), "Company "+company.getComp_name()+" is saved successully"));
		}
		else {
			 return ResponseEntity
					 .status(HttpStatus.INTERNAL_SERVER_ERROR)
					 .body(new ResponseDto(HttpStatus.OK.toString(), "Company "+company.getComp_name()+" is not saved "));
		}
	}

	@GetMapping("/") 	
	@ApiOperation("This End Point is used to get all companies list")
	public ResponseEntity<List<Company>> viewCompanies() {
			List<Company> clist = compserv.getAllCompanies();
			if(clist.size()>0){
				return new ResponseEntity<List<Company>>(clist,HttpStatus.OK) ;
			}
			else {
				return new ResponseEntity<List<Company>>(HttpStatus.NO_CONTENT) ;
			}
	}
	
	@GetMapping("/{id}")	 
	@ApiOperation("This End Point is used to get the company by company ID")
	public ResponseEntity<Company> editCompany(@PathVariable("id") Long cid) {
		Company comp = compserv.getCompanyById(cid);
		return new ResponseEntity<Company>(comp,HttpStatus.OK);
	}
	
//	@PutMapping("/")
//	@ApiOperation("This End point is used to Update the Company")
//	public ResponseEntity<List<Company>> updateCompany(@RequestBody Company comp) {
//		int res = compserv.updateCompany(comp);
//		if (res > 0){
//			return new ResponseEntity<List<Company>>(compserv.getAllCompanies(),HttpStatus.OK) ;
//		}
//		else {
//			return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);
//		}
//	}
	@PutMapping("/")
	@ApiOperation("This End point is used to Update the Company")
	public ResponseEntity<ResponseDto> updateCompany(@RequestBody Company comp) {
		int res = compserv.updateCompany(comp);
		if (res > 0){
		    return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Company "+comp.getComp_name()+" updated successfully"));
		}
		else{
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(new ResponseDto("304", "Company "+comp.getComp_name()+" is not updated "));
        }
	}
}
