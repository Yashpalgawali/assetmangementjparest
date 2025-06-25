package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Company;
import com.example.demo.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("company")
@Validated
@Tag(name = "Company",description = "Company Manangement API")
public class CompanyController {

	private final CompanyService compserv;

	public CompanyController(CompanyService compserv) {
		super();
		this.compserv = compserv;
	}

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/")
	@ApiResponses(value = 
				{
 					@ApiResponse(description = "Company is saved Successfully", responseCode = "200" ) ,
					@ApiResponse(description = "Company is NOT saved ", responseCode = "500" ) 
				})
	@Operation(summary = "Save Company",description = "This End Point is used to save the Company")
	@CacheEvict(allEntries = true,value = "companylist")
	public ResponseEntity<?> saveCompany(@Valid @RequestBody Company company)
	{
		Company comp = compserv.saveCompany(company);
		
		return ResponseEntity
					 .status(HttpStatus.CREATED)
					 .body(new ResponseDto(HttpStatus.CREATED.toString(), "Company "+comp.getComp_name()+" is saved successully"));
		
	}

	@GetMapping("/") 	
	@Operation(summary="Get List of Companies", description = "This End Point is used to get all companies list")
	@ApiResponses(value =
						{
							@ApiResponse(description = "Company is saved Successfully", responseCode = "200" ) ,
							@ApiResponse(description = "Company is NOT saved ", responseCode = "500" ) 
						})
	@Cacheable("companylist")
	public ResponseEntity<List<Company>> viewCompanies() {
			List<Company> clist = compserv.getAllCompanies();
			 
			if(clist.size() > 0) {
				return new ResponseEntity<List<Company>>(clist,HttpStatus.OK) ;			 
			}
			else {
				return ResponseEntity.notFound().build();
			}
	}

	@GetMapping("/{id}")
	@Operation(summary ="Retrieve Company", description = "This End point is used to retrieve the Company Details")
	@ApiResponses(value = 
				{
					@ApiResponse(description = "Company is found ", responseCode = "200" ),
					@ApiResponse(description = "Company is NOT found", responseCode = "404" ) 
				})
	public ResponseEntity<Company> editCompany(@PathVariable("id") Long cid) {
		Company comp = compserv.getCompanyById(cid);
		return new ResponseEntity<Company>(comp,HttpStatus.OK);
	}
 
	@PutMapping("/")
	@Operation(summary ="Update Company", description = "This End point is used to Update the Company")
	@ApiResponses(value = 
				{
					@ApiResponse(description = "Company is Updated Successfully", responseCode = "200" ),
					@ApiResponse(description = "Company is NOT Updated ", responseCode = "304" ) 
				})
	@CacheEvict(allEntries = true,value = "companylist")
	public ResponseEntity<?> updateCompany(@Valid @RequestBody Company comp) {
		compserv.updateCompany(comp);
		 
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto(HttpStatus.OK.toString(), "Company " + comp.getComp_name() + " Updated successfully"));

	}
}
