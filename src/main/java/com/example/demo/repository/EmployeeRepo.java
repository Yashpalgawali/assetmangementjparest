package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

import jakarta.transaction.Transactional;

@Repository("emprepo")
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Modifying
	@Transactional
	@Query("UPDATE Employee e SET e.emp_name=:ename,e.emp_email=:email,e.emp_contact=:cont,e.department.dept_id=:depid,e.designation.desig_id=:desigid WHERE e.emp_id=:eid")
	public int updateEmployee(String ename, String email, String cont, Long depid, Long desigid, Long eid);

	@Query("SELECT e FROM Employee e join e.designation JOIN e.department JOIN e.department.company")
	public List<Employee> getAllEmployees();

//	@Query("SELECT e FROM Employee e join e.designation JOIN e.department JOIN e.department.company WHERE e.emp_id=:eid")
	@Query("SELECT e FROM Employee e WHERE e.emp_id=:eid")
	public Optional<Employee> getEmployeeById(Long eid);
}
