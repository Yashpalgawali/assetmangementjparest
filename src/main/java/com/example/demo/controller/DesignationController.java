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

@RestController
@RequestMapping("/designation")
public class DesignationController {

	private final DesignationService desigserv;

	public DesignationController(DesignationService desigserv) {
		super();
		this.desigserv = desigserv;
	}

	@PostMapping("/")
//	@ApiOperation(value = "This End Point Will save the designation")
//	@ApiResponses(value = 
//				{
//					@ApiResponse(message = "Designation is saved successfully ", code = 200 ) ,
//					@ApiResponse(message = "Designation is not saved ", code = 500 ) 
//				})
	public ResponseEntity<ResponseDto> saveDesignation(@RequestBody Designation desig) throws NoContentException {
		Designation designation = desigserv.saveDesignation(desig);
		if(designation!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(HttpStatus.CREATED.toString() ,"Designtion "+desig.getDesig_name()+" is saved successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString() ,"Designtion "+desig.getDesig_name()+" is not saved"));
		}
	}

	@GetMapping("/")
//	@ApiOperation(value = "This Will return all designations list")
//	@ApiResponses(value = 
//				{
//					@ApiResponse(message = "Designation is saved successfully ", code = 200 ) ,
//					@ApiResponse(message = "Designation is not saved ", code = 500 ) 
//				})
	public ResponseEntity<List<Designation>> viewDesignations() throws NoContentException {
		return new ResponseEntity<List<Designation>>(desigserv.getAllDesignations(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
//	@ApiOperation(value = "This Will get the Designation by ID")
//	@ApiResponses(value = 
//				{
//					@ApiResponse(message = "Designation is fetched successfully ", code = 200 ) ,
//					@ApiResponse(message = "Designation is not found", code = 404 ) 
//				})
	public ResponseEntity<Designation> editDesignationById(@PathVariable Long id) {
		Designation desig = desigserv.getDesignationById(id);
		return new ResponseEntity<Designation>(desig, HttpStatus.OK);
	}

	@PutMapping("/")
//	@ApiOperation(value = "This Will update the designation")
//	@ApiResponses(value = 
//				{
//					@ApiResponse(message = "Designation is updated successfully ", code = 200 ) ,
//					@ApiResponse(message = "Designation is not updated", code = 304 ) 
//				})
	public ResponseEntity<ResponseDto> updateDesignation(@RequestBody Designation desig)
			throws NoContentException {
		int res = desigserv.updateDesignation(desig);
		if (res > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),"Designation "+desig.getDesig_name()+" is updated successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString(),"Designation "+desig.getDesig_name()+" is not updated successfully"));
		}
	}

}
