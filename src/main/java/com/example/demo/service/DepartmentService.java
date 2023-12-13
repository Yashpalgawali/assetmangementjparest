package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Department;

public interface DepartmentService {

	public Department saveDepartment(Department dept);
	
	public List<Department> getAllDepartments();
	
	public Department getDepartmentById(Long deptid);
	
	public int updateDepartment(Department dept);
	
	public List<Department> getDepartmentByCompanyId(Long cid);
	
<<<<<<< HEAD
	public List<Department> getDepartmentByCompanyName(String cname);
	
=======
>>>>>>> branch 'master' of https://github.com/Yashpalgawali/assetmangementjparest.git
}
