package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.models.Department;
import com.example.demo.service.DepartmentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("department")
public class DepartmentController {

	private final DepartmentService deptserv;

	public DepartmentController(DepartmentService deptserv) {
		super();
		this.deptserv = deptserv;		 
	}

	@PostMapping("/")
	@ApiOperation("This End Point is used to save Department")
	public ResponseEntity<ResponseDto> saveDepartment(@RequestBody Department dept) {
		
		Department depart = deptserv.saveDepartment(dept);
		if(depart!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Department "+dept.getDept_name()+" is saved successfully"));
		}	
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Department "+dept.getDept_name()+" is not saved "));
		}
	}
	
	@GetMapping("/")
	@ApiOperation("This End Point is used to get all Departments list")
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
	@ApiOperation("This End Point is used to get department by department ID")
	public ResponseEntity<Department> getDepartmentByDeptId(@PathVariable Long id){
		Department dept = deptserv.getDepartmentById(id);
		if(dept!=null) {
			return new ResponseEntity<Department>(dept,HttpStatus.OK);
		}
		else{
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/")
	@ApiOperation("This End Point is used to update the department")
	public ResponseEntity<ResponseDto> updateDepartment(@RequestBody Department dept)
	{
		int result = deptserv.updateDepartment(dept);
		if(result > 0 ){
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Department "+dept.getDept_name()+" is saved successfully"));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto(HttpStatus.NOT_MODIFIED.toString(), "Department "+dept.getDept_name()+" is not updated"));
		}
	}
	
	@GetMapping("/getdeptbycompid/{id}")
	@ApiOperation("This End Point is used to get departments by using company ID")
	public ResponseEntity<List<Department>> getDepartmentByCompanyId(@PathVariable Long id){
		return new ResponseEntity<List<Department>>(deptserv.getDepartmentByCompanyId(id),HttpStatus.OK);
	}
	
	@GetMapping("/getdeptbycompname/{name}")
	@ApiOperation("This End Point is used to get departments by using company Name")
	public ResponseEntity<List<Department>> getDepartmentByCompanyName(@PathVariable String name){
		return new ResponseEntity<List<Department>>(deptserv.getDepartmentByCompanyName(name),HttpStatus.OK);
	}

}
