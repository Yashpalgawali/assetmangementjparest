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
import com.example.demo.models.Department;
import com.example.demo.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("department")
@Validated
@Tag(name = "Department", description = "Department Management API")
public class DepartmentController {

	private final DepartmentService deptserv;

	public DepartmentController(DepartmentService deptserv) {
		super();
		this.deptserv = deptserv;
	}

	 
	@PostMapping("/")
	@Operation(description = "This End Point is used to save Department", summary = "Save Department")
	@ApiResponses(value = {
	  @ApiResponse(description = "Department is saved successfully ",  responseCode = "200") ,
	  @ApiResponse(description = "Department is not saved ", responseCode = "500")  })
	 
	public ResponseEntity<ResponseDto> saveDepartment(@Valid @RequestBody Department dept) {

		Department depart = deptserv.saveDepartment(dept);
		if (depart != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),
					"Department " + dept.getDept_name() + " is saved successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
							"Department " + dept.getDept_name() + " is not saved "));
		}
	}

	@GetMapping("/")
	@Operation(description = "This End Point is used to get all Departments list", summary = "List of Departments")
	@ApiResponses(value = {
			@ApiResponse(description = "Department List is fetched successfully ", responseCode = "200"),
			@ApiResponse(description = "No Departments are found", responseCode = "404") })
	public ResponseEntity<List<Department>> viewAllDepartments() {
		List<Department> dlist = deptserv.getAllDepartments();
		if (dlist.size() > 0) {
			return new ResponseEntity<List<Department>>(dlist, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Department>>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	@Operation(description = "This End Point is used to retrieve details of a Department", summary = "Department Details")
	@ApiResponses(value = { @ApiResponse(description = "Department is fetched successfully ", responseCode = "200"),
			@ApiResponse(description = "No Department is found for given ID", responseCode = "404") })
	public ResponseEntity<Department> getDepartmentByDeptId(@PathVariable Long id) {
		Department dept = deptserv.getDepartmentById(id);
		if (dept != null) {
			return new ResponseEntity<Department>(dept, HttpStatus.OK);
		} else {
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/")
	@Operation(description = "This End Point is used to Update the Department", summary = "Update Department")
	@ApiResponses(value = { @ApiResponse(description = "Department is Updated successfully ", responseCode = "200"),
			@ApiResponse(description = "Department is Not Updated ", responseCode = "404") })
	public ResponseEntity<ResponseDto> updateDepartment(@Valid @RequestBody Department dept) {
		int result = deptserv.updateDepartment(dept);
		if (result > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),
					"Department " + dept.getDept_name() + " is updated successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseDto(
					HttpStatus.NOT_MODIFIED.toString(), "Department " + dept.getDept_name() + " is not updated"));
		}
	}

	@GetMapping("/getdeptbycompid/{id}")
	@Operation(description = "This End Point is used to get Departments by using Company ID", summary = "Get Departments By company ID")
	@ApiResponses(value = { @ApiResponse(description = "Department of Company is found ", responseCode = "200"),
			@ApiResponse(description = "No Department is Not found ", responseCode = "404") })
	public ResponseEntity<List<Department>> getDepartmentByCompanyId(@PathVariable Long id) {
		return new ResponseEntity<List<Department>>(deptserv.getDepartmentByCompanyId(id), HttpStatus.OK);
	}

	@GetMapping("/getdeptbycompname/{name}")
	@Operation(description = "This End Point is used to get the List of Departments by using Company Name", summary = "Get Departments by Company Name")
	@ApiResponses(value = { @ApiResponse(description = "Department of Company is found ", responseCode = "200"),
			@ApiResponse(description = "No Department is Not Updated ", responseCode = "304") })
	public ResponseEntity<List<Department>> getDepartmentByCompanyName(@PathVariable String name) {
		return new ResponseEntity<List<Department>>(deptserv.getDepartmentByCompanyName(name), HttpStatus.OK);
	}

}
