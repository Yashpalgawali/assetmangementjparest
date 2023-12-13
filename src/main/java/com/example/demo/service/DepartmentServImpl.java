package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Department;
import com.example.demo.repository.DepartmentRepo;

@Service("deptserv")
public class DepartmentServImpl implements DepartmentService {

	@Autowired
	DepartmentRepo deptrepo;
	
	
	@Override
	public Department saveDepartment(Department dept) {
		if(dept!=null) {
			return deptrepo.save(dept);
		}
		else {
			return null;
		}
	}

	@Override
	public List<Department> getAllDepartments() {
		return deptrepo.findAll();
	}

	@Override
	public Department getDepartmentById(Long deptid) {
		try {
			return  deptrepo.findById(deptid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateDepartment(Department dept) {
		return deptrepo.updateDepartmentById(dept.getDept_name(), dept.getCompany().getComp_id(), dept.getDept_id());
	}

	@Override
	public List<Department> getDepartmentByCompanyId(Long cid) {
		return deptrepo.getAllDepartmentsByCompanyId(cid);
	}

	@Override
	public List<Department> getDepartmentByCompanyName(String cname) {
		return deptrepo.getAllDepartmentsByCompanyName(cname);
	}

}
