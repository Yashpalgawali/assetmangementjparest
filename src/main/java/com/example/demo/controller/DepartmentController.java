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
@RequestMapping("department")
@CrossOrigin("*")
public class DepartmentController {

	@Autowired
	DepartmentService deptserv;
	
	@Autowired
	CompanyService compserv;

	
	@PostMapping("/")
	public ResponseEntity<List<Department>> saveDepartment(@RequestBody Department dept) {
		
		Department depart = deptserv.saveDepartment(dept);
		if(depart!=null) {
			return new ResponseEntity<List<Department>>(deptserv.getAllDepartments() ,HttpStatus.OK);
		}	
		else {
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Department>> viewAllDepartments(){
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
	
	@PutMapping("/")
	public ResponseEntity<List<Department>> updateDepartment(@RequestBody Department dept)
	{
		int result = deptserv.updateDepartment(dept);
		if(result > 0 ){
			return new ResponseEntity<List<Department>>(deptserv.getAllDepartments() ,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Department>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getdeptbycompid/{id}")
	public ResponseEntity<List<Department>> getDepartmentByCompanyId(@PathVariable("id")Long id){
		return new ResponseEntity<List<Department>>(deptserv.getDepartmentByCompanyId(id),HttpStatus.OK);
	}
	
	@GetMapping("/getdeptbycompname/{name}")
	public ResponseEntity<List<Department>> getDepartmentByCompanyName(@PathVariable("name")String name){
		return new ResponseEntity<List<Department>>(deptserv.getDepartmentByCompanyName(name),HttpStatus.OK);
	}

}
