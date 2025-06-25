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
import com.example.demo.exceptions.NoContentException;
import com.example.demo.models.Designation;
import com.example.demo.service.DesignationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/designation")
@Tag(name="Designation", description = "Designation Management API")
public class DesignationController {

	private final DesignationService desigserv;

	public DesignationController(DesignationService desigserv) {
		super();
		this.desigserv = desigserv;
	}

	@PostMapping("/")
	@Operation(summary= "Save Designation ", description = "This End Point Will save the designation")
	@ApiResponses(value = 
				{
					@ApiResponse(description = "Designation is saved successfully ", responseCode = "200" ) ,
					@ApiResponse(description = "Designation is not saved ", responseCode  = "500" ) 
				})
	public ResponseEntity<ResponseDto> saveDesignation(@RequestBody Designation desig) throws NoContentException {
		Designation designation = desigserv.saveDesignation(desig);
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString() ,"Designtion "+desig.getDesig_name()+" is saved successfully"));
		
	}

	@GetMapping("/")
	@Operation(summary ="List of Designations ", description = "This Will return all designations list")
	@ApiResponses(value = 
				{
					@ApiResponse(description = "Designation is saved successfully ", responseCode = "200" ) ,
					@ApiResponse(description = "Designation is not saved ", responseCode = "500" ) 
				})
	public ResponseEntity<List<Designation>> viewDesignations()  {
		return new ResponseEntity<List<Designation>>(desigserv.getAllDesignations(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary="Get Desingnation", description = "This Will retrieve the Designation details by ID")
	@ApiResponses(value = 
				{
					@ApiResponse(description = "Designation is fetched successfully ",responseCode = "200" ) ,
					@ApiResponse(description = "Designation is not found", responseCode = "404" ) 
				})
	public ResponseEntity<Designation> editDesignationById(@PathVariable Long id) {
		Designation desig = desigserv.getDesignationById(id);
		return new ResponseEntity<Designation>(desig, HttpStatus.OK);
	}

	@PutMapping("/")
	@Operation(description = "This Will Update the Designation",summary = "Update Designation Details")
	@ApiResponses(value = 
				{
					@ApiResponse(description = "Designation is updated successfully ", responseCode = "200" ) ,
					@ApiResponse(description = "Designation is not updated", responseCode  = "304" ) 
				})
	public ResponseEntity<ResponseDto> updateDesignation(@RequestBody Designation desig) {
		int res = desigserv.updateDesignation(desig);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),"Designation "+desig.getDesig_name()+" is updated successfully"));
		
	}

}
