package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.models.Department;
import com.example.demo.service.CompanyService;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/department")
@CrossOrigin("*")
public class DepartmentController {

	@Autowired
	DepartmentService deptserv;
	
	@Autowired
	CompanyService compserv;

	
	@PostMapping("/")
	public ResponseEntity<List<Department>> saveDepartment(@RequestBody Department dept)
	{
		System.err.println("Inside savedept method end point");
		Department depart = deptserv.saveDepartment(dept);
		
		if(depart!=null){
			return new ResponseEntity<List<Department>>(deptserv.getAllDepartments() ,HttpStatus.OK);
		}	
		else {
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Department>> viewAllDepartments(){
		System.err.println("inside get all departments method\n");
		List<Department> dlist = deptserv.getAllDepartments();
		
		if(dlist.size()>0){
			return new ResponseEntity<List<Department>>(dlist ,HttpStatus.OK);
		}	
		else {
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartmentByDeptId(@PathVariable("id")Long id){
		Department dept = deptserv.getDepartmentById(id);
		if(dept!=null) {
			return new ResponseEntity<Department>(dept,HttpStatus.OK);
		}
		else{
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PutMapping("/updatedepartment")
	public ResponseEntity<List<Department>> updateDepartment(@RequestBody Department dept)
	{System.err.println("inside updatedepartment method \nName = "+dept.getDept_name()+" \n Company Id "+dept.getCompany().getComp_id());
		int result = deptserv.updateDepartment(dept);
		if(result > 0 ){
			return new ResponseEntity<List<Department>>(deptserv.getAllDepartments() ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Department>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@RequestMapping("/getdeptbycompid/{id}")
//	public ResponseEntity<List<Department>> getDepartmentByCompanyId(@PathVariable("id")Long id){
//		return new ResponseEntity<List<Department>>(deptserv.getDepartmentByCompanyId(id),HttpStatus.OK);
//	}
	
//	@GetMapping("/viewdepartments")
//	public String viewAllDepartments(Model model)
//	{
//		List<Department> dlist = deptserv.getAllDepartments();
//		model.addAttribute("dlist", dlist);
//		return "ViewDepartments";
//	}
//	@RequestMapping("/savedepartment")
//	public String saveDepartment(@ModelAttribute("Department") Department dept,RedirectAttributes attr)
//	{
//		Department depart = deptserv.saveDepartment(dept);
//		
//		if(depart!=null)
//		{
//			attr.addFlashAttribute("response", "Department is saved Successfully");
//			return "redirect:/viewdepartments";
//		}	
//		else {
//			attr.addFlashAttribute("reserr", "Department is not saved ");
//			return "redirect:/viewdepartments";
//		}
//	}
	
//	@GetMapping("/editdeptbyid/{id}")
//	public String getDepartmentByDeptId(@PathVariable("id")String id,Model model,RedirectAttributes attr)
//	{
//		Department dept = deptserv.getDepartmentById(id);
//		if(dept!=null)
//		{
//			model.addAttribute("clist", compserv.getAllCompanies());
//			model.addAttribute("dept", dept);
//			return "EditDepartment";
//		}
//		else
//		{
//			attr.addFlashAttribute("reserr", "No Department Found");
//			return "redirect:/viewdepartments";
//		}
//	}
	
	
//	@RequestMapping("/updatedepartment")
//	public String updateDepartment(@ModelAttribute("Department")Department dept,RedirectAttributes attr)
//	{
//		int result = deptserv.updateDepartment(dept);
//		if(result > 0 )
//		{
//			attr.addFlashAttribute("response", "Department is updated Successfully");
//			return "redirect:/viewdepartments";
//		}
//		else
//		{
//			attr.addFlashAttribute("reserr", "Department is not Updated ");
//			return "redirect:/viewdepartments";
//		}
//		
//	}
	
	
	
	
	
	
}
