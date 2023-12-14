package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Designation;
import com.example.demo.service.DesignationService;

@RestController
@RequestMapping("/designation")
public class DesignationController {

	@Autowired
	DesignationService desigserv;
	
	@PostMapping("/")
	public ResponseEntity<List<Designation>> saveDesignation(@RequestBody Designation desig) {
		Designation designation = desigserv.saveDesignation(desig);
		if(designation !=null){
			return new ResponseEntity<List<Designation>>( desigserv.getAllDesignations(), HttpStatus.OK );
		}
		else {
			return new ResponseEntity<List<Designation>>( HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Designation>> viewDesignations() {
		return new ResponseEntity<List<Designation>>(desigserv.getAllDesignations(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Designation> editDesignationById(@PathVariable("id")Long id) {
		Designation desig = desigserv.getDesignationById(id);
		if(desig!=null) {
			return new ResponseEntity<Designation>( desig, HttpStatus.OK );
		}
		else {
			return new ResponseEntity<Designation>( HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<List<Designation>> updateDesignation(@RequestBody Designation desig){
		int res= desigserv.updateDesignation(desig);
		if(res>0) {
			return new ResponseEntity<List<Designation>>( desigserv.getAllDesignations(), HttpStatus.OK );
		}
		else {
			return new ResponseEntity<List<Designation>>( HttpStatus.NO_CONTENT);
		}
	}

}
