package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Department;

@Repository("deptrepo")
public interface DepartmentRepo extends JpaRepository<Department, Long> {

	
	@Query(value="UPDATE  Department d SET d.dept_name=?1,d.company.comp_id=?2 WHERE d.dept_id=?3")
	@Modifying
	@Transactional
	public int updateDepartmentById(String depname,Long cid,Long depid);
	
	
	@Query("SELECT d FROM Department d JOIN d.company company WHERE d.company.comp_id=:cid")// JOIN using JPQL
	public List<Department> getAllDepartmentsByCompanyId(Long cid);
	
}
