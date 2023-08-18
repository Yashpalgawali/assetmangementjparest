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
		// TODO Auto-generated method stub
		if(dept!=null)
		{
			return deptrepo.save(dept);
		}
		else {
			return null;
		}
	}

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return deptrepo.findAll();
	}

	@Override
	public Department getDepartmentById(Long deptid) {
		// TODO Auto-generated method stub
		try {
			return  deptrepo.findById(deptid).get();
		}
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public int updateDepartment(Department dept) {
		// TODO Auto-generated method stub
		return deptrepo.updateDepartmentById(dept.getDept_name(), dept.getCompany().getComp_id(), dept.getDept_id());
	}

	@Override
	public List<Department> getDepartmentByCompanyId(Long cid) {
		// TODO Auto-generated method stub
		
		return deptrepo.getAllDepartmentsByCompanyId(""+cid);
	}

}
