package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Company;
import com.example.demo.service.CompanyService;

@RestController
@RequestMapping("company")
@Validated
public class CompanyController {

	private final CompanyService compserv;

	public CompanyController(CompanyService compserv) {
		super();
		this.compserv = compserv;
	}

//	@ApiOperation(summary=" Save company" , description = "This End Point is used to save the Company")
//	@ApiResponses(value = 
//				@ApiResponse(responseCode = "200" ,description = "Company is saved successfully" )		
//			)
//	@ApiOperation(value = "This End Point is used to save the Company")
//	@ApiResponses(value = 
//				{
//					@ApiResponse(message = "Company is saved Successfully", code = 200 ) ,
//					@ApiResponse(message = "Company is NOT saved ", code = 500 ) 
//				})
	@PostMapping("/")
	public ResponseEntity<ResponseDto> saveCompany(@Valid @RequestBody Company company)
	{
		Company comp = compserv.saveCompany(company);
		if(comp!=null) {
			 return ResponseEntity
					 .status(HttpStatus.CREATED)
					 .body(new ResponseDto(HttpStatus.CREATED.toString(), "Company "+company.getComp_name()+" is saved successully"));
		}
		else {
			 return ResponseEntity
					 .status(HttpStatus.INTERNAL_SERVER_ERROR)
					 .body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Company "+company.getComp_name()+" is not saved "));
		}
	}

	@GetMapping("/") 	
//	@ApiOperation(value = "This End Point is used to get all companies list")
//	@ApiResponses(value = 
//				{
//					@ApiResponse(message = "Company List is found", code = 200 ) ,
//					@ApiResponse(message = "No Companies found ", code = 500 ) 
//				})
	public ResponseEntity<List<Company>> viewCompanies() {
			List<Company> clist = compserv.getAllCompanies();
			return new ResponseEntity<List<Company>>(clist,HttpStatus.OK) ;			 
	}
	
	@GetMapping("/{id}")
//	@ApiOperation(value = "This End Point is used to get the company by company ID")
//	@ApiResponses(value = 
//				{
//					@ApiResponse(message = "Company is found", code = 200 ) ,
//					@ApiResponse(message = "Company is NOT found ", code = 500 ) 
//				})
	public ResponseEntity<Company> editCompany(@PathVariable("id") Long cid) {
		Company comp = compserv.getCompanyById(cid);
		return new ResponseEntity<Company>(comp,HttpStatus.OK);
	}
	
	@PutMapping("/")
//	@ApiOperation(value = "This End point is used to Update the Company")
//	@ApiResponses(value = 
//				{
//					@ApiResponse(message = "Company is Updated successfully", code = 200 ) ,
//					@ApiResponse(message = "Company is NOT updated ", code = 304 ) 
//				})
	public ResponseEntity<ResponseDto> updateCompany(@Valid @RequestBody Company comp) {
		int res = compserv.updateCompany(comp);
		if (res > 0){
		    return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.OK.toString(), "Company "+comp.getComp_name()+" updated successfully"));
		}
		else{
            return ResponseEntity
                    .status(HttpStatus.NOT_MODIFIED)
                    .body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString() , "Company "+comp.getComp_name()+" is not updated "));
        }
	}
}
