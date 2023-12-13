package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("*")
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

=======
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

import com.example.demo.models.Department;
import com.example.demo.models.Designation;
import com.example.demo.service.DesignationService;

@RestController
@RequestMapping("/designation")
@CrossOrigin("*")
public class DesignationController {

	@Autowired
	DesignationService desigserv;
	
	@PostMapping("/")
	public ResponseEntity<List<Designation>> saveDesignation(@RequestBody Designation desig){
		Designation designation = desigserv.saveDesignation(desig);
		if(designation !=null){
			return new ResponseEntity<List<Designation>>( desigserv.getAllDesignations(), HttpStatus.OK );
		}
		else {
			return new ResponseEntity<List<Designation>>( HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Designation>> viewDesignations(){
		return new ResponseEntity<List<Designation>>(desigserv.getAllDesignations(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Designation> editDesignationById(@PathVariable("id")Long id)
	{
		Designation desig = desigserv.getDesignationById(id);
		if(desig!=null) {
			return new ResponseEntity<Designation>( desig, HttpStatus.OK );
		}
		else {
			return new ResponseEntity<Designation>( HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/updatedesignation")
	public ResponseEntity<List<Designation>> updateDesignation(@RequestBody Designation desig){
		int res= desigserv.updateDesignation(desig);
		if(res>0){
			return new ResponseEntity<List<Designation>>( desigserv.getAllDesignations(), HttpStatus.OK );
		}
		else {
			return new ResponseEntity<List<Designation>>( HttpStatus.NO_CONTENT);
		}
	}
	
//	@GetMapping("/adddesignation")
//	public String addDesignation()
//	{
//		return "AddDesignation";
//	}
	
//	@RequestMapping("/savedesignation")
//	public String saveDesignation(@ModelAttribute("Designation")Designation desig,RedirectAttributes attr)
//	{
//		Designation designation = desigserv.saveDesignation(desig);
//		
//		if(designation !=null)
//		{
//			attr.addFlashAttribute("response", "Designation is Saved Successfully");
//			return "redirect:/viewdesignation";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "Designation is not Saved ");
//			return "redirect:/viewdesignation";
//		}
//	}
	
//	@GetMapping("/viewdesignation")
//	public String viewDesignations(Model model)
//	{
//		List<Designation> dlist = desigserv.getAllDesignations();
//		
//		model.addAttribute("dlist", dlist);
//		return "ViewDesignation";
//	}
//	
//	@GetMapping("/editdesignation/{id}")
//	public String editDesignationById(@PathVariable("id")String id,Model model,RedirectAttributes attr)
//	{
//		Designation desig = desigserv.getDesignationById(id);
//		if(desig!=null)
//		{
//			model.addAttribute("desig", desig);
//			return "EditDesignation";
//		}
//		else {
//			attr.addFlashAttribute("reserr", "No designation found for given Id");
//			return "redirect:/viewdesignation";
//		}
//	}
//	
//	@RequestMapping("/updatedesignation")
//	public String updateDesignation(@ModelAttribute("Designation") Designation desig,RedirectAttributes attr)
//	{
//		int res= desigserv.updateDesignation(desig);
//		if(res>0)
//		{
//			attr.addFlashAttribute("response", "Designation is Updated Successfully");
//			return "redirect:/viewdesignation";
//		}
//		else {
//		
//			attr.addFlashAttribute("reserr", "Designation is not Updated");
//			return "redirect:/viewdesignation";
//		}
//	
//	}
>>>>>>> branch 'master' of https://github.com/Yashpalgawali/assetmangementjparest.git
}
